<?php
$host = 'localhost';
$port = '3306';
$db = 'mynetflix';
$user = 'mynetflix';
$pwd = '@dmNetflix';

try {
    $bdd = new PDO("mysql:host=$host;port=$port;dbname=$db;charset=utf8", $user, $pwd);
} catch(Exception $e) {
    die('Erreur de connection : ' . $e->getMessage());
}
