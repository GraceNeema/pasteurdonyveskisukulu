<?php

 if(isset($_POST['target']) and $_POST['target'] != ""){
    switch($_POST['target']){

        /* quand il faut que je recupere une actualité ou toutes les actualites */
        case 'get_actu':
            require_once ('actualite.class.php');
            $actu = new Actualite();
            if(isset($_POST['idactualite']) and $_POST['idactualite'] != ""){
                echo $actu->getOne($_POST['idactualite']);
            }else{
                echo $actu->getAll();
            }
            break;
        
        case 'get_meditation' :
              require_once ('meditation.class.php');
              $meditation= new Meditation();
              echo $meditation->getAll();
              break;
              
        case 'get_theme' :
              require_once ('theme.class.php');
              $theme= new Theme();
              echo $theme->getAll();
              break;
        case 'get_predication' :
              require_once ('predication.class.php');
              $predication= new Predication();
              echo $predication->getAll();
              break;      

        default:
            echo "404";
            break; 


    }
}else{
    echo "404";
}



