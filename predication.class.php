<?php

class Predication{

    var $idpredication = 0;
    var $url="inconnue";
    var $texte = "inconnue";
    var $texte_entier="inconnue";
    var $theme="inconnue";
 
   
    function Predication(){

    }


   

    function getAll(){
        require_once ('database.class.php');
        $db = new Database();
        $data = $db->vLink->query("SELECT * FROM predication")->fetchAll();
        return json_encode($data);
    }


}