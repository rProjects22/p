<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Assignment 1</title>
    <style>
        .holder{
            display:flex;
            flex-direction: row;
            width:100%;
            max-width:900px;
            gap:20px;
        }
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        p {
            font-size: 20px;
        }
        h1{
            font-size: 25px;
        }
        input[type=submit], #phoneTypes {
            font-size: 14px;
        }
    </style>
</head>
<body>
<h1>Client Information</h1>
<?php
//php function to get the IP Address.
function getIpAddress(){
    //whether ip is from the share internet
    if(!empty($_SERVER['HTTP_CLIENT_IP'])){
        $ip = $_SERVER['HTTP_CLIENT_IP'];
    }
    //whether ip is from the proxy
    elseif(!empty($_SERVER['HTTP_X_FORWARDED_FOR'])){
        $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
    }
    //whether ip is from the remote address
    else{
        $ip = $_SERVER['REMOTE_ADDR'];
    }
    return $ip;
}
$ip = getIpAddress();
echo 'IP Address of the client: '.$ip;
echo "<br>";
echo "<br>";

// get the user browser type
echo "Browser used by the client: ".$_SERVER['HTTP_USER_AGENT'];
echo "<br>";
echo "<br>";
//get the date and time the website was accessed.
$myDate = date("d-m-y h:i:s");
echo "Date and Time this website Accessed: ".$myDate;
?>
<br>
<h1>SmartPhone Shop</h1>
<br>
<hr>
<?php
$total = 0.0;

if (isset($_GET['checkout'])) {
    $products = $_GET['products'];
    foreach($products as $product){
        //strip $ from the price
        $price = substr($product, strpos($product, "$") + 1);

        $total += (float)$price;
    }
}
    //$phoneType = $_GET['phoneTypes'];

$products = array(
    "apple" => array(
        "Iphone11" => "$450.50",
        "Iphone12" => "$570.75",
        "Iphone13" => "$760.99",
    ),
    "samsung" => array(
        "GalaxyZ" => "$560.99",
        "GalaxyA" => "$670.50",
        "GalaxyS" => "$750.90",
    ),
    "huawei" => array(
        "HuaweiP20" => "$685.75",
        "HuaweiP30" => "$935.50",
        "HuaweiP50" => "$1285.99",
    )
);
    //$array_map = [];
    //foreach ($products as $key => $value) {
     //   $array_map[$key] = ($value[key($value)]);
    //}
    //echo implode('<br>', $array_map);



    //echo $phoneType;
    // if (!empty($_GET['phoneTypes'])) {
    //     $selection = $_GET['phoneTypes'];
    //     echo $selection;
    // }


// loop over array

?>



<form method="GET" action="101337672.php">
    <!--looping through the array to display the products as checkboxes.-->
    <?php
    echo'<div class="holder">';
        foreach ($products as $key => $value) {

            echo "<div>$key.<br>";
            foreach ($value as $model => $price) {
                echo "<input type='checkbox' name='products[]' value='$price'>$model.:.$price<br>";
            }
            echo "</div>";
        }
    echo'</div><br>';
    ?>
    <input type="submit" name="checkout" value="Checkout">
</form>

<p> Total: $<?php echo $total; ?></p>
<a href="101337672.php">Reset</a>
</body>
</html>