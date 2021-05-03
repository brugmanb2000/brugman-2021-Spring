<?php 

require_once "dao.php";
$request = $_GET['request'];
$gamePIN = $_GET['gamePIN'];
$nickname = $_GET['nickname'];
$userType = $_GET['userType'];
$item = $_GET['item'];
$vote = $_GET['vote'];
$sanitizedNickname = filter_var($nickname, FILTER_SANITIZE_SPECIAL_CHARS);
$sanitizedItem = filter_var($item, FILTER_SANITIZE_SPECIAL_CHARS);


try {
    
    $dao = new Dao();

    switch($request) {
 
        case ("testPin"):
            echo ($dao->availablePIN($gamePIN));
            break;
 
         case ("addPin"):
            echo ($dao->addPIN($gamePIN, $sanitizedNickname));
            break;

        case ("removePin"):
            echo ($dao->removePIN($gamePIN));
            break;

        case ("addItem"):
            echo ($dao->addItem($gamePIN, $sanitizedItem));
            break;

        case ("updateGameState"):
            echo ($dao->updateGameState($gamePIN));
            break;

        case ("getGameState"):
            echo ($dao->getGameState($gamePIN));
            break;

        case ("getItems"):
            echo ($dao->getItems($gamePIN));
            break;

        case ("removeItem"):
            echo ($dao->removeItem($gamePIN, $sanitizedItem));
            break;

        case ("addNickname"):
            echo ($dao->addNickname($gamePIN, $sanitizedNickname, $userType));
            break;

        case ("removeNickname"):
            echo ($dao->removeNickname($gamePIN, $sanitizedNickname));
            break;

        case ("getNicknames"):
            echo ($dao->getNicknames($gamePIN));
            break;

        case ("addVotes"):
            echo ($dao->addVote($gamePIN, $sanitizedItem, $vote));
            break;

        case ("getVotes"):
            echo ($dao->getVotes($gamePIN));
            break;

        case ("clearSession"):
            echo ($dao->clearSession($gamePIN));
            break;


        default:
        echo "No valid request found";
        break;
    }
     } catch (PDOException $e) {
            echo "PDO Exception: " . $e->getMessage();
        }
?>