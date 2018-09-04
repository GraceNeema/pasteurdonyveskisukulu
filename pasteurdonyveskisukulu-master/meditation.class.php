<?php

class Meditation{

    var $idmeditation = 0;
    var $titre = "inconnue";
    var $soustitre="inconnue";
    var $message = "pas de message";
    var $date = "inconnue";
    var $image = "images/01.jpg";
   
    function Meditation(){

    }


   

    function getAll(){
        require_once ('database.class.php');
        $db = new Database();
        $data = $db->vLink->query("SELECT * FROM meditation")->fetchAll();
        return json_encode($data);
    }


}
