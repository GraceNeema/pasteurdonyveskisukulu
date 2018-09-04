<?php

class Actualite{

    var $idactualite = 0;
    var $titre = "inconnue";
    var $message = "pas de message";
    var $date = "inconnue";
    var $image = "images/01.jpg";

    function Actualite(){

    }


    function getOne($_idactualite){
        require_once ('database.class.php');
        $db = new Database();
        $data = $db->vLink->query("SELECT * FROM actualite WHERE idactualite=".$_idactualite)->fetch();
        return json_encode($data);
    }

    function getAll(){
        require_once ('database.class.php');
        $db = new Database();
        $data = $db->vLink->query("SELECT * FROM actualite")->fetchAll();
        return json_encode($data);
    }


}


