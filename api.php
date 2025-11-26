<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");

// Handle preflight OPTIONS request
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    exit(0);
}

$conn = new mysqli("127.0.0.1", "sakshi", "Sakshipw", "student_management");

if ($conn->connect_error) {
    die(json_encode(["error" => "Connection failed: " . $conn->connect_error]));
}

$method = $_SERVER['REQUEST_METHOD'];
$action = $_GET['action'] ?? '';

// GET all students
if ($method == 'GET' && $action == 'getAll') {
    $sql = "SELECT * FROM students ORDER BY average_marks DESC";
    $result = $conn->query($sql);
    $students = [];
    while($row = $result->fetch_assoc()) {
        $students[] = $row;
    }
    echo json_encode($students);
}

// POST new student
if ($method == 'POST' && $action == 'add') {
    $data = json_decode(file_get_contents("php://input"), true);
    
    $name = $conn->real_escape_string($data['name']);
    $roll = intval($data['rollNumber']);
    $math = floatval($data['mathMarks']);
    $science = floatval($data['scienceMarks']);
    $english = floatval($data['englishMarks']);
    $total = $math + $science + $english;
    $average = $total / 3;
    
    $grade = 'F';
    if ($average >= 90) $grade = 'A+';
    else if ($average >= 80) $grade = 'A';
    else if ($average >= 70) $grade = 'B';
    else if ($average >= 60) $grade = 'C';
    else if ($average >= 50) $grade = 'D';
    
    $sql = "INSERT INTO students (name, roll_number, math_marks, science_marks, english_marks, total_marks, average_marks, grade) 
            VALUES ('$name', $roll, $math, $science, $english, $total, $average, '$grade')";
    
    if ($conn->query($sql)) {
        echo json_encode(["success" => true, "message" => "Student added to database!"]);
    } else {
        echo json_encode(["error" => $conn->error]);
    }
}

// DELETE student
if ($method == 'POST' && $action == 'delete') {
    $data = json_decode(file_get_contents("php://input"), true);
    $roll = intval($data['rollNumber']);
    
    $sql = "DELETE FROM students WHERE roll_number = $roll";
    
    if ($conn->query($sql)) {
        echo json_encode(["success" => true, "message" => "Student deleted from database!"]);
    } else {
        echo json_encode(["error" => $conn->error]);
    }
}

// DELETE all students
if ($method == 'POST' && $action == 'deleteAll') {
    $sql = "DELETE FROM students";
    
    if ($conn->query($sql)) {
        echo json_encode(["success" => true, "message" => "All students deleted from database!"]);
    } else {
        echo json_encode(["error" => $conn->error]);
    }
}

$conn->close();
?>