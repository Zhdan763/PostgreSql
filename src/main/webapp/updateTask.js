let dateInput = document.getElementById('date');
dateInput.min = new Date().toISOString().slice(0, -8)
