<script>
        const API_URL = 'http://localhost:8000/api.php';

        // Load students when page loads
        window.addEventListener('load', () => {
            loadStudents();
            checkConnection();
        });

        // Check database connection
        async function checkConnection() {
            try {
                const response = await fetch(`${API_URL}?action=getAll`);
                if (response.ok) {
                    document.getElementById('connectionStatus').textContent = '✅ Connected to MySQL Database';
                    document.getElementById('connectionStatus').classList.remove('error');
                } else {
                    throw new Error('Connection failed');
                }
            } catch (error) {
                document.getElementById('connectionStatus').textContent = '❌ Database Connection Failed';
                document.getElementById('connectionStatus').classList.add('error');
            }
        }

        // Load all students from database
        async function loadStudents() {
            try {
                const response = await fetch(`${API_URL}?action=getAll`);
                const students = await response.json();
                
                renderStudents(students);
                updateStats(students);
            } catch (error) {
                console.error('Error loading students:', error);
                document.getElementById('studentList').innerHTML = `
                    <div class="empty-state">
                        <div class="empty-state-icon">❌</div>
                        <p>Error loading students from database. Make sure PHP server is running!</p>
                        <p style="font-size: 0.9em; margin-top: 10px;">Run: php -S localhost:8000</p>
                    </div>
                `;
            }
        }

        // Add new student
        document.getElementById('studentForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const studentData = {
                name: document.getElementById('name').value,
                rollNumber: parseInt(document.getElementById('rollNumber').value),
                mathMarks: parseFloat(document.getElementById('math').value),
                scienceMarks: parseFloat(document.getElementById('science').value),
                englishMarks: parseFloat(document.getElementById('english').value)
            };

            try {
                const response = await fetch(`${API_URL}?action=add`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(studentData)
                });

                const result = await response.json();
                
                if (result.success) {
                    alert('✅ Student added to database successfully!');
                    this.reset();
                    loadStudents(); // Reload from database
                } else {
                    alert('❌ Error: ' + (result.error || 'Failed to add student'));
                }
            } catch (error) {
                alert('❌ Error connecting to database. Make sure PHP server is running!');
                console.error(error);
            }
        });

        // Render students
        function renderStudents(students) {
            const container = document.getElementById('studentList');
            
            if (students.length === 0) {
                container.innerHTML = `
                    <div class="empty-state">
                        <div class="empty-state-icon">📋</div>
                        <p>No students in database. Add your first student!</p>
                    </div>
                `;
                return;
            }

            container.innerHTML = students.map(student => {
                const gradeClass = student.grade.toLowerCase().replace('+', '-plus');
                return `
                    <div class="student-card">
                        <div class="student-header">
                            <div class="student-info">
                                <h3>${student.name}</h3>
                                <p class="roll-number">Roll #${student.roll_number}</p>
                            </div>
                            <div class="grade-badge grade-${gradeClass}">
                                ${student.grade}
                            </div>
                        </div>
                        
                        <div class="marks-grid">
                            <div class="mark-item">
                                <div class="mark-label">Mathematics</div>
                                <div class="mark-value">${student.math_marks}</div>
                            </div>
                            <div class="mark-item">
                                <div class="mark-label">Science</div>
                                <div class="mark-value">${student.science_marks}</div>
                            </div>
                            <div class="mark-item">
                                <div class="mark-label">English</div>
                                <div class="mark-value">${student.english_marks}</div>
                            </div>
                        </div>

                        <div class="summary-grid">
                            <div class="summary-item">
                                <span class="summary-label">Total Marks:</span>
                                <span class="summary-value">${student.total_marks}/300</span>
                            </div>
                            <div class="summary-item">
                                <span class="summary-label">Average:</span>
                                <span class="summary-value">${parseFloat(student.average_marks).toFixed(2)}%</span>
                            </div>
                        </div>

                        <button class="delete-btn" onclick="deleteStudent(${student.roll_number})">🗑️ Remove from Database</button>
                    </div>
                `;
            }).join('');
        }

        // Delete student
        async function deleteStudent(rollNumber) {
            if (!confirm('Are you sure you want to remove this student from the database?')) {
                return;
            }

            try {
                const response = await fetch(`${API_URL}?action=delete`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ rollNumber })
                });

                const result = await response.json();
                
                if (result.success) {
                    alert('✅ Student removed from database!');
                    loadStudents();
                } else {
                    alert('❌ Error: ' + (result.error || 'Failed to delete student'));
                }
            } catch (error) {
                alert('❌ Error connecting to database');
                console.error(error);
            }
        }

        // Clear all students
        async function clearAll() {
            if (!confirm('Are you sure you want to delete ALL students from the database? This cannot be undone!')) {
                return;
            }

            try {
                const response = await fetch(`${API_URL}?action=deleteAll`, {
                    method: 'POST'
                });

                const result = await response.json();
                
                if (result.success) {
                    alert('✅ All students deleted from database!');
                    loadStudents();
                } else {
                    alert('❌ Error: ' + (result.error || 'Failed to delete students'));
                }
            } catch (error) {
                alert('❌ Error connecting to database');
                console.error(error);
            }
        }

        // Update statistics
        function updateStats(students) {
            const total = students.length;
            document.getElementById('totalStudents').textContent = total;

            if (total === 0) {
                document.getElementById('avgScore').textContent = '0.0';
                document.getElementById('highestScore').textContent = '0';
                document.getElementById('lowestScore').textContent = '0';
                return;
            }

            const totals = students.map(s => parseFloat(s.total_marks));
            const avgScore = totals.reduce((a, b) => a + b, 0) / total;
            const highestScore = Math.max(...totals);
            const lowestScore = Math.min(...totals);

            document.getElementById('avgScore').textContent = avgScore.toFixed(1);
            document.getElementById('highestScore').textContent = highestScore;
            document.getElementById('lowestScore').textContent = lowestScore;
        }
    </script>