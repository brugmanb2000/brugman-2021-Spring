<?php

class Dao {

private $db_user = "bfef313794a63b";
private $db_pass = "0941b479";
private $db_host = "us-cdbr-east-03.cleardb.com:3306";
private $db_name = "heroku_4b802321a0323b7";


public function getConnection() {
    try {
        return new PDO("mysql:host={$this->db_host};dbname={$this->db_name}", $this->db_user, $this->db_pass);
    } catch (PDOException $e) {
        echo "Connection Failed: " . $e->getMessage();
    }
}

public function availablePIN($pin) {
    $conn = $this->getConnection();
    $retVal = $conn->prepare("Select Count(*) from active_sessionPINs where session_PIN like $pin;");
    $retVal->execute();
    $data = $retVal->fetch();
    if (intval($data[0]) > 0) {
        $value = (array('ReturnStatus' => 'false'));
        return json_encode(array($value));
    } else {
        $value = (array('ReturnStatus' => 'true'));
        return json_encode(array($value));
    }
}

public function addPIN($pin, $nickname) {
    $conn = $this->getConnection();
    try {
    $sql = $conn->prepare("Insert into active_sessionPINS (session_PIN) VALUES ($pin);");
    $sql->execute();
    $array = array("ReturnStatus" => "Complete");
    return json_encode(array($array));
    } catch (PDOException $e) {
        $array = array("ReturnStatus" => "Duplicate PIN");
        return json_encode(array($array));
    }
}

public function removePIN($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Delete from active_sessionPINS WHERE session_PIN like $pin;");
    $sql->execute();
    $array = array("ReturnStatus" => "Complete");
    return json_encode(array($array));
}

public function addItem($pin, $item) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Select count(item) from users_items where item like '$item' and session_PIN = $pin;");
    $retVal = $sql->execute();
    $retVal = $sql->fetchColumn();
    if ($retVal == 0) {
        $sql2 = $conn->prepare("Insert into users_items (session_PIN, item) VALUES ($pin, '$item');");
        $sql2->execute();
        return json_encode(array(array("ReturnStatus" => "added")));
    } else {
        return json_encode(array(array("ReturnStatus" => "duplicate")));
    }
}

public function getItems($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Select item from users_items where session_PIN = $pin;");
    $sql->execute();
    $retVal = $sql->fetchAll(PDO::FETCH_ASSOC);
    return json_encode(($retVal), JSON_PRETTY_PRINT);
}

public function removeItem($pin, $item) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Delete from users_items where session_PIN like $pin AND item like '$item';");
    $sql->execute();
    return json_encode(array((array("ReturnStatus" => "removed"))));
}

public function clearSession($pin) {
    $conn = $this->getConnection();
    $sql1 = $conn->prepare("Delete from users_items where session_PIN like $pin;");
    $sql2 = $conn->prepare("Delete from active_sessionsPINS where session_PIN like $pin;");
    $sql3 = $conn->prepare("Delete from users where session_PIN like $pin;");
    $sql4 = $conn->prepare("Delete from vote_results where session_PIN like $pin");
    $sql1->execute();
    $sql2->execute();
    $sql3->execute();
    $sql4->execute();
    return json_encode(array(array("ReturnStatus" =>  "PIN cleared from database")));
}

public function addNickname($pin, $nickname, $userType) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Insert into users (session_PIN, nickname, userType, matchStart) VALUES ($pin, '$nickname', '$userType', 0);");
    $sql->execute();
    $array = array("ReturnStatus" => "Complete");
    return json_encode(array($array));
}

public function removeNickname($pin, $nickname) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("DELETE FROM users WHERE session_PIN LIKE $pin AND nickname LIKE '$nickname';");
    $sql->execute();
    return json_encode(array(array("ReturnStatus" => "removed")));
}

public function updateGameState($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Update users SET matchStart = 1 where session_PIN = $pin and userType like 'host';");
    $sql->execute();
    $array = array("ReturnStatus" => "Complete");
    return json_encode(array($array));
}

public function getGameState($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("SELECT matchStart from users where session_PIN = $pin AND userType = 'host';");
    $sql->execute();
    $array = array("ReturnStatus" => $sql->fetchColumn());
    return json_encode(array($array));
}

public function getNicknames($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("Select nickname from users where session_PIN = $pin;");
    $sql->execute();
    $retVal = $sql->fetchAll(PDO::FETCH_ASSOC);
    return json_encode($retVal, JSON_PRETTY_PRINT);
}

public function getVotes($pin) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("SELECT item, YesCount, NoCount from vote_results where session_PIN = $pin");
    $sql->execute();
    $retVal = $sql->fetchAll(PDO::FETCH_ASSOC);
    return json_encode($retVal, JSON_PRETTY_PRINT);
}

public function addVote($pin, $item, $vote) {
    $conn = $this->getConnection();
    $sql = $conn->prepare("SELECT EXISTS(SELECT item from vote_results where item like '$item' and session_PIN = $pin) as 'UniqueItem';");
    $sql->execute();
    $retVal = $sql->fetchColumn();
    

    if ($retVal == 1) {
  
        if ($vote == 'yes') {
            $sql2 = $conn->prepare("Update vote_results SET YesCount = YesCount + 1 where session_PIN = $pin and item like '$item';");
            $sql2->execute();
            return json_encode(array("ReturnStatus" => "$vote added"));
        } else {
            $sql2 = $conn->prepare("Update vote_results SET NoCount = NoCount + 1 where session_PIN = $pin and item like '$item';");
            $sql2->execute();
            return json_encode(array("ReturnStatus" => "$vote added"));
        }

    } else {
       $sql2 = $conn->prepare("Insert into vote_results(session_PIN, item) values ($pin, '$item');");
       $sql2->execute();
       $this->addVote($pin, $item, $vote);

    }
}
}
?>