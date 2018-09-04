<?php

class Theme{

    var $idtheme = 0;
    var $titre="inconnue";
    var $image_ref = "images/01.jpg";
 
   
    function Theme(){

    }


   

    function getAll(){
        require_once ('database.class.php');
        $db = new Database();
        $data = $db->vLink->query("SELECT * FROM theme")->fetchAll();
        return json_encode($data);
    }


}
