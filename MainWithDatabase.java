const API_URL = "http://localhost:8000/api.php";

/* =========================
   PAGE LOAD
========================= */
window.addEventListener("load", () => {
    checkConnection();
    loadStudents();
});

/* =========================
   CHECK DATABASE CONNECTION
========================= */
async function checkConnection() {
    try {
        const response = await fetch(`${API_URL}?action=getAll`);
        if (response.ok) {
            document.getElementById("connectionStatus").textContent =
                "✅ Connected to Database";
            document.getElementById("connectionStatus").classList.remove("error");
        } else {
            throw new Error();
        }
    } catch {
        document.getElementById("connectionStatus").textContent =
            "❌ Database Connection Failed";
        document.getElementById("connectionStatus").classList.add("error");
    }
}

/* =========================
   LOAD STUDENTS FROM DB
========================= */
async function loadStudents() {
    try {
        const response = await fetch(`${API_URL}?action=getAll`);
        const students = await response.json();
        renderStudents(students);
        updateStats(students);
    } catch (error) {
        console.error(error);
        document.getElementById("studentList").innerHTML =
            "<p style='color:red'>Failed to load students</p>";
    }
}

/* =========================
   ADD STUDENT
========================= */
document.getElementById("studentForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const student = {
        name: document.getElementById("name").value,
        rollNumber: parseInt(document.getElementById("rollNumber").value),
        mathMarks: parseFloat(document.getElementById("math").value),
        scienceMarks: parseFloat(document.getElementById("science").value),
        englishMarks: parseFloat(document.getElementById("english").value),
    };

    try {
        const response = await fetch(`${API_URL}?action=add`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(student),
        });

        const result = await response.json();

        if (result.success) {
            alert("✅ Student added successfully!");
            document.getElementById("studentForm").reset();
            loadStudents(); // 🔥 THIS refreshes UI from DB
        } else {
            alert("❌ Error: " + result.error);
        }
    } catch (error) {
        console.error(error);
        alert("❌ Server error");
    }
});

/* =========================
   RENDER STUDENTS ON UI
========================= */
function renderStudents(students) {
    const container = document.getElementById("studentList");

    if (students.length === 0) {
        container.innerHTML = "<p>No students found</p>";
        return;
    }

    container.innerHTML = students
        .map(
            (s) => `
        <div class="student-card">
            <h3>${s.name}</h3>
            <p>Roll: ${s.roll_number}</p>
            <p>Math: ${s.math_marks}</p>
            <p>Science: ${s.science_marks}</p>
            <p>English: ${s.english_marks}</p>
            <p>Total: ${s.total_marks}</p>
            <p>Average: ${parseFloat(s.average_marks).toFixed(2)}%</p>
            <p>Grade: ${s.grade}</p>
            <button onclick="deleteStudent(${s.roll_number})">🗑 Delete</button>
        </div>
    `
        )
        .join("");
}

/* =========================
   DELETE ONE STUDENT
========================= */
async function deleteStudent(rollNumber) {
    if (!confirm("Delete this student?")) return;

    try {
        const response = await fetch(`${API_URL}?action=delete`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ rollNumber }),
        });

        const result = await response.json();
        if (result.success) {
            alert("✅ Student deleted");
            loadStudents();
        } else {
            alert("❌ Delete failed");
        }
    } catch (error) {
        console.error(error);
    }
}

/* =========================
   DELETE ALL STUDENTS
========================= */
async function clearAll() {
    if (!confirm("Delete ALL students?")) return;

    try {
        const response = await fetch(`${API_URL}?action=deleteAll`, {
            method: "POST",
        });

        const result = await response.json();
        if (result.success) {
            alert("✅ All students deleted");
            loadStudents();
        } else {
            alert("❌ Failed to clear data");
        }
    } catch (error) {
        console.error(error);
    }
}

/* =========================
   UPDATE STATISTICS
========================= */
function updateStats(students) {
    const total = students.length;
    document.getElementById("totalStudents").textContent = total;

    if (total === 0) {
        document.getElementById("avgScore").textContent = "0";
        document.getElementById("highestScore").textContent = "0";
        document.getElementById("lowestScore").textContent = "0";
        return;
    }

    const totals = students.map((s) => parseFloat(s.total_marks));
    const avg = totals.reduce((a, b) => a + b, 0) / total;

    document.getElementById("avgScore").textContent = avg.toFixed(1);
    document.getElementById("highestScore").textContent = Math.max(...totals);
    document.getElementById("lowestScore").textContent = Math.min(...totals);
}
