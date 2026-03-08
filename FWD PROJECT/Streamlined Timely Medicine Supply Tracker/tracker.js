// Course Outcome 5: ES6 modules
// Course Outcome 3: JavaScript Fundamentals

let medicines = [];
let snoozedMedicines = {};

// Course Outcome 3: Objects - Object literals
const INTERACTION_WARNINGS = {
  'warfarin': ['aspirin', 'ibuprofen'],
  'aspirin': ['warfarin', 'ibuprofen'],
  'ibuprofen': ['aspirin', 'warfarin'],
  'metformin': ['alcohol'],
  'lisinopril': ['potassium'],
  'simvastatin': ['grapefruit', 'amiodarone'],  // Cholesterol + grapefruit + heart med
  'digoxin': ['verapamil', 'quinidine'],        // Heart medication interactions
  'levothyroxine': ['calcium', 'iron'],         // Thyroid + supplements
  'sildenafil': ['nitrates']                    // ED medication + heart medication
};


// Course Outcome 3: Number literals
const LOW_STOCK_THRESHOLD = 0x05; // Hexadecimal: 5
const SNOOZE_DURATION = 6e5; // Scientific notation: 600000 ms (10 minutes)

// Course Outcome 4: Browser storage
function saveMedicines() {
  try {
    localStorage.setItem('medicines', JSON.stringify(medicines));
  } catch (error) {
    // Course Outcome 5: Exception handling
    console.error('Failed to save medicines:', error);
    alert('Error saving data. Please check your browser settings.');
  }
}

// Course Outcome 4: Asynchronous programming - Promises
// Course Outcome 5: API integration (simulated)
async function fetchWeather() {
  try {
    // Simulated weather API - in production use: fetch('https://api.openweathermap.org/...')
    await new Promise(resolve => setTimeout(resolve, 500));
    
    const temps = [68, 72, 75, 80, 65];
    const icons = ['☀️', '⛅', '🌤️', '🌧️', '❄️'];
    const temp = temps[Math.floor(Math.random() * temps.length)];
    const icon = icons[Math.floor(Math.random() * icons.length)];
    
    document.getElementById('weatherTemp').textContent = temp + '°F';
    document.getElementById('weatherIcon').textContent = icon;
  } catch (error) {
    console.error('Weather fetch failed:', error);
    document.getElementById('weatherWidget').style.display = 'none';
  }
}

// Course Outcome 5: Form validation with JavaScript
function validateForm(formData) {
  const errors = [];
  
  // Course Outcome 3: Conditions and operators
  if (!formData.name || formData.name.length < 2) {
    errors.push('Medicine name must be at least 2 characters');
  }
  
  if (!formData.dosage) {
    errors.push('Dosage is required');
  }
  
  if (!formData.time) {
    errors.push('Time is required');
  }
  
  if (formData.stock && (formData.stock < 0 || formData.stock > 1000)) {
    errors.push('Stock must be between 0 and 1000');
  }
  
  return errors;
}

// Course Outcome 4: Event handling and DOM manipulation
document.getElementById('medicineForm').addEventListener('submit', function(e) {
  e.preventDefault();
  
  // Course Outcome 5: Handling user input dynamically
  const formData = {
    name: document.getElementById('name').value,
    dosage: document.getElementById('dosage').value,
    time: document.getElementById('time').value,
    stock: parseInt(document.getElementById('stock').value) || 0,
    refillDate: document.getElementById('refillDate').value,
    category: document.getElementById('category').value,
    notes: document.getElementById('notes').value,
    holidayMode: document.getElementById('holidayMode').checked
  };
  
  const errors = validateForm(formData);
  if (errors.length > 0) {
    alert('Validation Errors:\n' + errors.join('\n'));
    return;
  }
  
  // Course Outcome 3: Objects - Creating and manipulating objects
  const medicine = {
    id: Date.now(),
    ...formData,
    taken: false,
    takenAt: null,
    takenDate: null
  };
  
  medicines.push(medicine);
  saveMedicines();
  renderTables();
  checkInteractions();
  setupReminders();
  
  this.reset();
});

// Course Outcome 3: Array methods - filter
function cleanOldTakenMedicines() {
  const now = new Date();
  const twentyFourHoursAgo = new Date(now.getTime() - 24 * 60 * 60 * 1000);
  
  medicines = medicines.filter(med => {
    if (med.taken && med.takenDate) {
      const takenDate = new Date(med.takenDate);
      return takenDate > twentyFourHoursAgo;
    }
    return true;
  });
  
  saveMedicines();
}

// Course Outcome 3: Functions and Loops
function checkInteractions() {
  // Course Outcome 3: Array methods - filter, map
  const activeMeds = medicines
    .filter(m => !m.taken)
    .map(m => m.name.toLowerCase());
  
  const warnings = [];
  
  // Course Outcome 3: Loops - forEach
  activeMeds.forEach(med => {
    if (INTERACTION_WARNINGS[med]) {
      INTERACTION_WARNINGS[med].forEach(interacts => {
        if (activeMeds.includes(interacts)) {
          warnings.push(`⚠️ ${med.toUpperCase()} may interact with ${interacts.toUpperCase()}`);
        }
      });
    }
  });
  
  // Course Outcome 4: DOM manipulation
  const warningDiv = document.getElementById('interactionWarning');
  if (warnings.length > 0) {
    warningDiv.innerHTML = `
      <div class="interaction-warning">
        <strong>⚠️ Medicine Interaction Warning</strong><br>
        ${warnings.join('<br>')}
      </div>
    `;
  } else {
    warningDiv.innerHTML = '';
  }
}

function updateProgress() {
  const total = medicines.length;
  const taken = medicines.filter(m => m.taken).length;
  
  // Course Outcome 3: Basic expressions and operators
  const percentage = total > 0 ? Math.round((taken / total) * 100) : 0;
  
  document.getElementById('progressText').textContent = `${taken} / ${total} completed`;
  document.getElementById('progressBar').style.width = percentage + '%';
  document.getElementById('progressBar').textContent = percentage + '%';
  document.querySelector('.progress-bar-container').setAttribute('aria-valuenow', percentage);
}

function renderTables() {
  const searchTerm = document.getElementById('searchInput').value.toLowerCase();
  const categoryFilter = document.getElementById('filterCategory').value;
  const now = new Date();
  const currentTime = now.toTimeString().slice(0, 5);
  
  let pendingMeds = medicines.filter(m => !m.taken);
  let takenMeds = medicines.filter(m => m.taken);
  
  // Search filtering
  if (searchTerm) {
    pendingMeds = pendingMeds.filter(m => 
      m.name.toLowerCase().includes(searchTerm) || 
      m.notes.toLowerCase().includes(searchTerm)
    );
    takenMeds = takenMeds.filter(m => 
      m.name.toLowerCase().includes(searchTerm) || 
      m.notes.toLowerCase().includes(searchTerm)
    );
  }
  
  // Category filtering
  if (categoryFilter) {
    pendingMeds = pendingMeds.filter(m => m.category === categoryFilter);
    takenMeds = takenMeds.filter(m => m.category === categoryFilter);
  }
  
  const pendingBody = document.getElementById('pendingTableBody');
  const takenBody = document.getElementById('takenTableBody');
  
  // Course Outcome 3: Conditions
  if (pendingMeds.length === 0) {
    pendingBody.innerHTML = '<tr><td colspan="7" style="text-align:center;padding:40px;color:#999;">No pending medicines</td></tr>';
  } else {
    // Course Outcome 3: Array methods - map
    pendingBody.innerHTML = pendingMeds.map(med => {
      const status = med.time < currentTime ? 'missed' : 'pending';
      const statusText = status === 'missed' ? '⚠ Missed' : 'Pending';
      const lowStock = med.stock > 0 && med.stock <= LOW_STOCK_THRESHOLD;
      const needsRefill = med.refillDate && new Date(med.refillDate) <= now;
      
      return `
        <tr id="row-${med.id}" class="${needsRefill ? 'warning-row' : ''}">
          <td><strong>${med.name}</strong></td>
          <td>${med.dosage}</td>
          <td>${formatTime(med.time)}</td>
          <td>
            ${med.stock > 0 ? med.stock : 'N/A'}
            ${lowStock ? '<br><span class="status-badge status-low-stock">Low</span>' : ''}
            ${needsRefill ? '<br><span class="status-badge status-refill">Refill</span>' : ''}
          </td>
          <td>${med.notes ? `<div class="notes-text">${med.notes}</div>` : '-'}</td>
          <td><span class="status-badge status-${status}">${statusText}</span></td>
          <td class="no-print">
            <button class="btn-small btn-take" onclick="window.markAsTaken(${med.id})" title="Mark ${med.name} as taken">✓ Take</button>
            <button class="btn-small btn-snooze" onclick="window.snoozeMedicine(${med.id})" title="Snooze ${med.name} for 10 minutes">💤 10min</button>
            <button class="btn-small btn-delete" onclick="window.deleteMedicine(${med.id})" title="Delete ${med.name}">✕ Delete</button>
          </td>
        </tr>
      `;
    }).join('');
  }
  
  if (takenMeds.length === 0) {
    takenBody.innerHTML = '<tr><td colspan="6" style="text-align:center;padding:40px;color:#999;">No medicines taken yet</td></tr>';
  } else {
    takenBody.innerHTML = takenMeds.map(med => `
      <tr class="taken-row">
        <td><strong>${med.name}</strong></td>
        <td>${med.dosage}</td>
        <td>${formatTime(med.time)}</td>
        <td>${med.takenAt ? formatTime(med.takenAt) : 'N/A'}</td>
        <td>${med.notes ? `<div class="notes-text">${med.notes}</div>` : '-'}</td>
        <td class="no-print">
          <button class="btn-small btn-delete" onclick="window.deleteMedicine(${med.id})" title="Delete ${med.name}">✕ Delete</button>
        </td>
      </tr>
    `).join('');
  }
  
  updateProgress();
}

// Course Outcome 3: Arrow functions
const formatTime = (time) => {
  const [hours, minutes] = time.split(':');
  const hour = parseInt(hours);
  const ampm = hour >= 12 ? 'PM' : 'AM';
  const displayHour = hour % 12 || 12;
  return `${displayHour}:${minutes} ${ampm}`;
};

// Course Outcome 3: Functions
function markAsTaken(id) {
  // Course Outcome 3: Array methods - find
  const medicine = medicines.find(m => m.id === id);
  if (medicine) {
    const now = new Date();
    medicine.taken = true;
    medicine.takenAt = now.toTimeString().slice(0, 5);
    medicine.takenDate = now.toISOString();
    if (medicine.stock > 0) medicine.stock--;
    saveMedicines();
    renderTables();
    checkInteractions();
  }
}

// Course Outcome 4: Asynchronous programming - setTimeout
function snoozeMedicine(id) {
  const medicine = medicines.find(m => m.id === id);
  if (medicine) {
    setTimeout(() => {
      alert(`⏰ Reminder: Time to take ${medicine.name} (${medicine.dosage})`);
      const row = document.getElementById(`row-${id}`);
      if (row) {
        row.classList.add('highlight');
        setTimeout(() => row.classList.remove('highlight'), 5000);
      }
    }, SNOOZE_DURATION);
    
    alert(`Snoozed ${medicine.name} for 10 minutes`);
  }
}

function deleteMedicine(id) {
  const medicine = medicines.find(m => m.id === id);
  const medicineName = medicine ? medicine.name : 'this medicine';
  
  if (confirm(`Are you sure you want to permanently delete ${medicineName}? This action cannot be undone.`)) {
    medicines = medicines.filter(m => m.id !== id);
    saveMedicines();
    renderTables();
    checkInteractions();
    alert(`✅ ${medicineName} has been deleted successfully.`);
  }
}

function markAllTaken() {
  if (confirm('Mark all pending medicines as taken?')) {
    const now = new Date();
    medicines.forEach(med => {
      if (!med.taken) {
        med.taken = true;
        med.takenAt = now.toTimeString().slice(0, 5);
        med.takenDate = now.toISOString();
        if (med.stock > 0) med.stock--;
      }
    });
    saveMedicines();
    renderTables();
    checkInteractions();
  }
}

function resetDaily() {
  if (confirm('Reset all statuses?')) {
    medicines.forEach(med => {
      med.taken = false;
      med.takenAt = null;
      med.takenDate = null;
    });
    saveMedicines();
    renderTables();
    checkInteractions();
  }
}

function filterMedicines() {
  renderTables();
}

function sortMedicines() {
  const sortBy = document.getElementById('sortBy').value;
  
  // Course Outcome 3: Array methods - sort with callback functions
  if (sortBy === 'time') {
    medicines.sort((a, b) => a.time.localeCompare(b.time));
  } else if (sortBy === 'name') {
    medicines.sort((a, b) => a.name.localeCompare(b.name));
  } else if (sortBy === 'status') {
    medicines.sort((a, b) => {
      if (a.taken === b.taken) return 0;
      return a.taken ? 1 : -1;
    });
  }
  
  saveMedicines();
  renderTables();
}

function setupReminders() {
  const now = new Date();
  
  medicines.forEach(med => {
    if (!med.taken) {
      const [hours, minutes] = med.time.split(':');
      const reminderTime = new Date();
      reminderTime.setHours(parseInt(hours), parseInt(minutes), 0, 0);
      
      const timeUntilReminder = reminderTime - now;
      
      if (timeUntilReminder > 0 && timeUntilReminder < 24 * 60 * 60 * 1000) {
        setTimeout(() => {
          alert(`⏰ Time to take: ${med.name} (${med.dosage})`);
          const row = document.getElementById(`row-${med.id}`);
          if (row) {
            row.classList.add('highlight');
            setTimeout(() => row.classList.remove('highlight'), 5000);
          }
        }, timeUntilReminder);
      }
    }
  });
}

// Course Outcome 4: Asynchronous programming - setInterval
function checkMidnightReset() {
  setInterval(() => {
    const now = new Date();
    if (now.getHours() === 0 && now.getMinutes() === 0) {
      medicines.forEach(med => {
        med.taken = false;
        med.takenAt = null;
      });
      saveMedicines();
      renderTables();
      checkInteractions();
    }
  }, 60000);
}

// Course Outcome 3: Arrow functions
const toggleMenu = () => {
  const menu = document.getElementById('navMenu');
  menu.classList.toggle('active');
};

// Make functions globally accessible
window.markAsTaken = markAsTaken;
window.snoozeMedicine = snoozeMedicine;
window.deleteMedicine = deleteMedicine;
window.markAllTaken = markAllTaken;
window.resetDaily = resetDaily;
window.filterMedicines = filterMedicines;
window.sortMedicines = sortMedicines;
window.toggleMenu = toggleMenu;

// Course Outcome 4: Event handling - load event
window.addEventListener('load', () => {
  const saved = localStorage.getItem('medicines');
  if (saved) {
    try {
      medicines = JSON.parse(saved);
      cleanOldTakenMedicines();
      renderTables();
      checkInteractions();
      setupReminders();
    } catch (error) {
      console.error('Error loading medicines:', error);
      medicines = [];
    }
  }
  fetchWeather();
  checkMidnightReset();
});