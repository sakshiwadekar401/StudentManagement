const API_URL = "http://localhost:8000/api.php";

/* =====================
   ON LOAD
===================== */
window.onload = () => {
    checkConnection();
    loadStudents();
};

/* =====================
   CHECK DB CONNECTION
===================== */
async function checkConnection() {
    try {
        const res = await fetch(`${API_URL}?action=getAll`);
        if (res.ok) {
            document.getElementById("connectionStatus").innerText = "✅ Connected";
        } else {
            throw new Error();
        }
    } catch (e) {
        document.getElementById("connectionStatus").innerText = "❌ Not Connected";
        console.error(e);
    }
}

/* =====================
   ADD STUDENT (UI → PHP → DB)
===================== */
async function addStudent() {
    const student = {
        name: document.getElementById("name").value,
        rollNumber: Number(document.getElementById("rollNumber").value),
        mathMarks: Number(document.getElementById("math").value),
        scienceMarks: Number(document.getElementById("science").value),
        englishMarks: Number(document.getElementById("english").value)
    };

    const res = await fetch(`${API_URL}?action=add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    });

    const result = await res.json();

    if (result.success) {
        alert("✅ SAVED TO DATABASE");
        loadStudents();
    } else {
        alert("❌ FAILED: " + result.error);
    }
}

/* =====================
   LOAD STUDENTS FROM DB
===================== */
async function loadStudents() {
    const res = await fetch(`${API_URL}?action=getAll`);
    const students = await res.json();
    renderStudents(students);
    updateStats(students);
}

/* =====================
   RENDER STUDENTS
===================== */
function renderStudents(students) {
    const box = document.getElementById("studentList");

    if (!students || students.length === 0) {
        box.innerHTML = "<p>No data</p>";
        return;
    }

    box.innerHTML = students.map(s => `
        <div class="student-card">
            <b>${s.name}</b> (Roll ${s.roll_number})<br>
            Math: ${s.math_marks},
            Science: ${s.science_marks},
            English: ${s.english_marks}<br>
            Avg: ${s.average_marks}% | Grade: ${s.grade}<br>
            <button onclick="deleteStudent(${s.roll_number})">Delete</button>
        </div>
    `).join("");
}

/* =====================
   DELETE ONE STUDENT
===================== */
async function deleteStudent(roll) {
    await fetch(`${API_URL}?action=delete`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ rollNumber: roll })
    });
    loadStudents();
}

/* =====================
   DELETE ALL
===================== */
async function clearAll() {
    await fetch(`${API_URL}?action=deleteAll`, { method: "POST" });
    loadStudents();
}

/* =====================
   UPDATE STATS
===================== */
function updateStats(students) {
    document.getElementById("totalStudents").innerText = students.length;

    if (students.length === 0) return;

    const totals = students.map(s => Number(s.total_marks));
    document.getElementById("avgScore").innerText =
        (totals.reduce((a, b) => a + b, 0) / students.length).toFixed(1);
    document.getElementById("highestScore").innerText = Math.max(...totals);
    document.getElementById("lowestScore").innerText = Math.min(...totals);
}
