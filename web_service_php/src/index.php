<?php
// error_reporting(0);

// Create folder for each user
session_start();
if (!isset($_SESSION['dir'])) {
    $_SESSION['dir'] = 'upload';
}
$dir = $_SESSION['dir'];
if (!file_exists($dir))
    mkdir($dir);

if (isset($_FILES["file"])) {
    $error = '';
    $success = '';
    try {
        $file = $dir . "/" . $_FILES["file"]["name"];
        move_uploaded_file($_FILES["file"]["tmp_name"], $file);
        $success = 'Successfully uploaded file at: <a href="/' . $file . '">/' . $file . ' </a><br>';
        $success .= 'View all uploaded file at: <a href="/' . $dir . '/">/' . $dir . ' </a>';
    } catch (Exception $e) {
        $error = $e->getMessage();
    }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>File upload</title>

    <!-- This is for UI only -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>

<body>
    <br />
    <br />
    <h3 class="display-4 text-center">File upload</h3>

    <br />
    <div class="container">
        <form method="post" enctype="multipart/form-data">
            Select file to upload:
            <input type="file" name="file" id="file">
            <br />
            <input type="submit">
        </form>
        <span style="color:red"><?php echo $error; ?></span>
        <span style="color:green"><?php echo $success; ?></span>
    </div>

</body>

<footer class="container">
    <br />
    <br />
    <br />

    <script>
        function prevLevel() {
            const url = new URL(origin);
            url.port = (parseInt(url.port) - 1).toString();
            location.href = url.toString();
        }

        function nextLevel() {
            const url = new URL(origin);
            url.port = (parseInt(url.port) + 1).toString();
            location.href = url.toString();
        }
    </script>

</footer>

</html>