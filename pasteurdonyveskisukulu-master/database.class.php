<?php
class Database {
    var $db_username;
    var $db_password;
    var $db_name;
    var $db_host;
    var $vLink;
    function Database(){
        $this->db_username = 'root';
        $this->db_password = '';
        $this->db_host = 'localhost';
        $this->db_name = 'pasteurdonyves';
        try{
            $this->vLink = new PDO("mysql:host=".$this->db_host.";dbname=".$this->db_name, $this->db_username, $this->db_password,
                array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8",
                    PDO::ATTR_ERRMODE => PDO::ERRMODE_WARNING));
        }catch(PDOException $ex) {
            echo $ex;
            die("</br>erreur lors de la connection a la base de donne");
            
        }
  $sql= "INSERT INTO actualite(`idactualite`,`titre`,`mesasge`,`date`,`image_ref`)VALUES(3,'Pasteur Don Yves rend visite aux orphelins','Voici le contenue de la visite du pasteur Don 
Yves Kisukulu à l\'orphelinat dont il a récement fait la visite avec quelques fidèles de son église.', sysdate() ,'images/01.jpg')";}}