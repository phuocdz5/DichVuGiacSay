<?php
	require "dbconnect.php";

	class Service {
    public $id,  $name, $description, $price, $img;

    public function __construct($id,  $name, $description, $price, $img) {
        $this->id = $id;
        $this->name = $name;
        $this->description = $description;
        $this->price = $price;
        $this->img = $img;
    }
	}

	$query = "select * from dichvu";

	$data = mysqli_query($connect, $query);
	$arr = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arr, new Service($row['idDichVu'], $row['tenDichVu'], $row['moTa'], $row['giaTien'], $row['img']));
	}

	echo json_encode($arr);

?>