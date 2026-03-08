import java.util.*;
import java.time.*;
import java.time.format.*;
import java.io.*;

/**
 * MEDICINE TRACKER SYSTEM - Complete DSA Application
 * Expanded Version with All Features from HTML/JavaScript Project
 * 
 * CO1: Searching (Linear, Binary) & Sorting (Merge, Quick, Bubble, Selection, Insertion)
 * CO2: ADTs, Singly & Doubly Linked Lists, Circular Lists, operations
 * CO3: Stack (undo/redo), Queue, Circular Queue, Deque, Priority Queue
 * CO4: HashMap (chaining), HashSet, Java Collections Framework
 * CO5: Practical Medicine Tracking Application with Reports
 * CO6: Complete Program Development with User Interface
 * 
 * Features matching HTML/JavaScript version:
 * - Medicine Management (add, delete, mark as taken)
 * - Emergency Contacts (doubly linked list)
 * - Progress Tracking & Reports
 * - Stock Management & Low Stock Alerts
 * - Medicine Interaction Warnings
 * - Search & Sort Operations
 * - Priority Queue for Urgent Medicines
 * - Adherence Rate Calculation
 * - Refill Date Tracking
 */

// ============================================================================
// MAIN APPLICATION CLASS - Entry Point
// ============================================================================
public class MedicineTrackerSystem {
    private static MedicineManager medicineManager;
    private static EmergencyContactManager emergencyManager;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        printBanner();
        
        medicineManager = new MedicineManager();
        emergencyManager = new EmergencyContactManager();
        
        loadSampleData();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: medicineManagementMenu(); break;
                case 2: emergencyInfoMenu(); break;
                case 3: reportsAndAnalyticsMenu(); break;
                case 4: searchAndSortMenu(); break;
                case 5: advancedDSAMenu(); break;
                case 6: 
                    System.out.println("\n✅ Thank you for using Medicine Tracker!");
                    System.out.println("Stay healthy! 💊");
                    running = false;
                    break;
                default: 
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    static void printBanner() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                                                    ║");
        System.out.println("║     💊 MEDICINE TRACKER SYSTEM - Java DSA 💊      ║");
        System.out.println("║                                                    ║");
        System.out.println("║     Comprehensive Data Structures & Algorithms     ║");
        System.out.println("║              Application for Healthcare            ║");
        System.out.println("║                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }
    
    static void displayMainMenu() {
        System.out.println("\n╔═══════════════ MAIN MENU ═══════════════╗");
        System.out.println("║                                          ║");
        System.out.println("║  1. 💊 Medicine Management               ║");
        System.out.println("║  2. 🚨 Emergency Information             ║");
        System.out.println("║  3. 📊 Reports & Analytics               ║");
        System.out.println("║  4. 🔍 Search & Sort Operations          ║");
        System.out.println("║  5. 🎓 Advanced DSA Demonstrations       ║");
        System.out.println("║  6. 🚪 Exit                              ║");
        System.out.println("║                                          ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
    
    static void medicineManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔═══════ MEDICINE MANAGEMENT ═══════╗");
            System.out.println("║ 1. Add New Medicine               ║");
            System.out.println("║ 2. View All Medicines             ║");
            System.out.println("║ 3. Mark Medicine as Taken         ║");
            System.out.println("║ 4. Delete Medicine                ║");
            System.out.println("║ 5. Update Medicine Stock          ║");
            System.out.println("║ 6. View Priority Queue            ║");
            System.out.println("║ 7. Back to Main Menu              ║");
            System.out.println("╚═══════════════════════════════════╝");
            
            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1: addMedicine(); break;
                case 2: medicineManager.displayAllMedicines(); break;
                case 3: markMedicineAsTaken(); break;
                case 4: deleteMedicine(); break;
                case 5: updateMedicineStock(); break;
                case 6: medicineManager.displayPriorityQueue(); break;
                case 7: back = true; break;
                default: System.out.println("❌ Invalid choice");
            }
        }
    }
    
    static void emergencyInfoMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔═══════ EMERGENCY INFORMATION ═══════╗");
            System.out.println("║ 1. Add Emergency Contact            ║");
            System.out.println("║ 2. View All Contacts                ║");
            System.out.println("║ 3. Search Contact                   ║");
            System.out.println("║ 4. Remove Contact                   ║");
            System.out.println("║ 5. View Contacts in Reverse         ║");
            System.out.println("║ 6. Back to Main Menu                ║");
            System.out.println("╚═════════════════════════════════════╝");
            
            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1: addEmergencyContact(); break;
                case 2: emergencyManager.displayAllContacts(); break;
                case 3: searchEmergencyContact(); break;
                case 4: removeEmergencyContact(); break;
                case 5: emergencyManager.displayContactsReverse(); break;
                case 6: back = true; break;
                default: System.out.println("❌ Invalid choice");
            }
        }
    }
    
    static void reportsAndAnalyticsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔═══════ REPORTS & ANALYTICS ═══════╗");
            System.out.println("║ 1. Daily Summary                  ║");
            System.out.println("║ 2. Adherence Rate                 ║");
            System.out.println("║ 3. Low Stock Alerts               ║");
            System.out.println("║ 4. Refill Reminders               ║");
            System.out.println("║ 5. Medicine Interactions          ║");
            System.out.println("║ 6. Category-wise Report           ║");
            System.out.println("║ 7. Export Data (Simulation)       ║");
            System.out.println("║ 8. Back to Main Menu              ║");
            System.out.println("╚═══════════════════════════════════╝");
            
            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1: medicineManager.displayDailySummary(); break;
                case 2: medicineManager.displayAdherenceRate(); break;
                case 3: medicineManager.displayLowStockAlert(); break;
                case 4: medicineManager.displayRefillReminders(); break;
                case 5: medicineManager.checkInteractionWarnings(); break;
                case 6: medicineManager.displayCategoryReport(); break;
                case 7: medicineManager.exportDataSimulation(); break;
                case 8: back = true; break;
                default: System.out.println("❌ Invalid choice");
            }
        }
    }
    
    static void searchAndSortMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔═══════ SEARCH & SORT (CO1) ═══════╗");
            System.out.println("║ 1. Linear Search                  ║");
            System.out.println("║ 2. Binary Search                  ║");
            System.out.println("║ 3. Merge Sort (by name)           ║");
            System.out.println("║ 4. Quick Sort (by time)           ║");
            System.out.println("║ 5. Bubble Sort (demo)             ║");
            System.out.println("║ 6. Selection Sort (demo)          ║");
            System.out.println("║ 7. Compare All Sorting Algorithms ║");
            System.out.println("║ 8. Back to Main Menu              ║");
            System.out.println("╚═══════════════════════════════════╝");
            
            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1: performLinearSearch(); break;
                case 2: performBinarySearch(); break;
                case 3: medicineManager.sortByNameMergeSort(); break;
                case 4: medicineManager.sortByTimeQuickSort(); break;
                case 5: medicineManager.bubbleSortDemo(); break;
                case 6: medicineManager.selectionSortDemo(); break;
                case 7: medicineManager.compareAllSorts(); break;
                case 8: back = true; break;
                default: System.out.println("❌ Invalid choice");
            }
        }
    }
    
    static void advancedDSAMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔═══════ ADVANCED DSA (CO2/CO3) ═══════╗");
            System.out.println("║ 1. Reverse Medicine List (CO2)       ║");
            System.out.println("║ 2. Detect Duplicate Times            ║");
            System.out.println("║ 3. Stack-based Undo/Redo (CO3)       ║");
            System.out.println("║ 4. Queue Medicine Schedule (CO3)     ║");
            System.out.println("║ 5. Circular Queue Demo (CO3)         ║");
            System.out.println("║ 6. Deque Operations (CO3)            ║");
            System.out.println("║ 7. HashMap Collision Demo (CO4)      ║");
            System.out.println("║ 8. Analyze Time Complexity (CO1)     ║");
            System.out.println("║ 9. Back to Main Menu                 ║");
            System.out.println("╚══════════════════════════════════════╝");
            
            int choice = getIntInput("Choice: ");
            switch (choice) {
                case 1: medicineManager.reverseLinkedList(); break;
                case 2: medicineManager.detectDuplicateTimes(); break;
                case 3: medicineManager.demonstrateUndoRedo(); break;
                case 4: medicineManager.displayMedicineQueue(); break;
                case 5: medicineManager.circularQueueDemo(); break;
                case 6: medicineManager.dequeDemo(); break;
                case 7: medicineManager.hashMapCollisionDemo(); break;
                case 8: medicineManager.analyzeComplexity(); break;
                case 9: back = true; break;
                default: System.out.println("❌ Invalid choice");
            }
        }
    }
    
    // Helper methods for menu operations
    static void addMedicine() {
        System.out.println("\n➕ ADD NEW MEDICINE");
        System.out.println("═══════════════════════════════════");
        
        String name = getStringInput("Medicine Name: ");
        String dosage = getStringInput("Dosage (e.g., 500mg): ");
        String time = getStringInput("Time (HH:MM format): ");
        int stock = getIntInput("Stock quantity: ");
        String category = getStringInput("Category (optional): ");
        String notes = getStringInput("Special instructions (optional): ");
        String refillDate = getStringInput("Refill date (YYYY-MM-DD, optional): ");
        
        Medicine medicine = new Medicine(name, dosage, time, stock, category, notes, refillDate);
        medicineManager.addMedicine(medicine);
        System.out.println("\n✅ Medicine added successfully!");
    }
    
    static void markMedicineAsTaken() {
        String name = getStringInput("\nEnter medicine name to mark as taken: ");
        if (medicineManager.markAsTaken(name)) {
            System.out.println("✅ Medicine marked as taken!");
        } else {
            System.out.println("❌ Medicine not found.");
        }
    }
    
    static void deleteMedicine() {
        medicineManager.displayAllMedicines();
        String name = getStringInput("\nEnter medicine name to delete: ");
        if (medicineManager.deleteMedicine(name)) {
            System.out.println("✅ Medicine deleted successfully!");
        } else {
            System.out.println("❌ Medicine not found.");
        }
    }
    
    static void updateMedicineStock() {
        String name = getStringInput("\nEnter medicine name: ");
        int newStock = getIntInput("Enter new stock quantity: ");
        if (medicineManager.updateStock(name, newStock)) {
            System.out.println("✅ Stock updated successfully!");
        } else {
            System.out.println("❌ Medicine not found.");
        }
    }
    
    static void addEmergencyContact() {
        System.out.println("\n➕ ADD EMERGENCY CONTACT");
        System.out.println("═══════════════════════════════════");
        
        String name = getStringInput("Contact Name: ");
        String phone = getStringInput("Phone Number: ");
        String relationship = getStringInput("Relationship: ");
        
        EmergencyContact contact = new EmergencyContact(name, phone, relationship);
        emergencyManager.addContact(contact);
        System.out.println("\n✅ Emergency contact added successfully!");
    }
    
    static void searchEmergencyContact() {
        String name = getStringInput("\nEnter contact name to search: ");
        EmergencyContact contact = emergencyManager.searchContact(name);
        if (contact != null) {
            System.out.println("\n✅ CONTACT FOUND:");
            System.out.println("═══════════════════════════════════");
            System.out.println(contact);
        } else {
            System.out.println("❌ Contact not found.");
        }
    }
    
    static void removeEmergencyContact() {
        emergencyManager.displayAllContacts();
        String name = getStringInput("\nEnter contact name to remove: ");
        if (emergencyManager.removeContact(name)) {
            System.out.println("✅ Contact removed successfully!");
        } else {
            System.out.println("❌ Contact not found.");
        }
    }
    
    static void performLinearSearch() {
        String name = getStringInput("\nEnter medicine name to search (Linear Search): ");
        long startTime = System.nanoTime();
        Medicine result = medicineManager.linearSearch(name);
        long endTime = System.nanoTime();
        
        if (result != null) {
            System.out.println("\n✅ FOUND:");
            System.out.println(result);
            System.out.println("\n⏱️  Time taken: " + (endTime - startTime) + " nanoseconds");
        } else {
            System.out.println("❌ Medicine not found.");
        }
    }
    
    static void performBinarySearch() {
        String name = getStringInput("\nEnter medicine name to search (Binary Search): ");
        System.out.println("ℹ️  Note: List will be sorted first for binary search...");
        
        long startTime = System.nanoTime();
        Medicine result = medicineManager.binarySearch(name);
        long endTime = System.nanoTime();
        
        if (result != null) {
            System.out.println("\n✅ FOUND:");
            System.out.println(result);
            System.out.println("\n⏱️  Time taken: " + (endTime - startTime) + " nanoseconds");
        } else {
            System.out.println("❌ Medicine not found.");
        }
    }
    
    static void loadSampleData() {
        // Add sample medicines
        medicineManager.addMedicine(new Medicine("Aspirin", "500mg", "08:00", 30, "pain", "Take with food", "2024-03-15"));
        medicineManager.addMedicine(new Medicine("Metformin", "850mg", "12:00", 15, "diabetes", "After lunch", "2024-02-28"));
        medicineManager.addMedicine(new Medicine("Lisinopril", "10mg", "20:00", 5, "blood-pressure", "Before bed", "2024-03-01"));
        medicineManager.addMedicine(new Medicine("Vitamin D", "1000IU", "09:00", 60, "vitamin", "Morning dose", "2024-04-10"));
        medicineManager.addMedicine(new Medicine("Ibuprofen", "400mg", "14:00", 25, "pain", "With water", ""));
        
        // Add sample emergency contacts
        emergencyManager.addContact(new EmergencyContact("Dr. Smith", "555-0101", "Primary Doctor"));
        emergencyManager.addContact(new EmergencyContact("John Doe", "555-0102", "Emergency Contact"));
        emergencyManager.addContact(new EmergencyContact("Hospital ER", "911", "Emergency Services"));
    }
    
    // Input utility methods
    static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
    }
}

// ============================================================================
// CO2: MEDICINE CLASS - Abstract Data Type (ADT)
// ============================================================================
class Medicine implements Comparable<Medicine> {
    // Instance variables
    private String name;
    private String dosage;
    private LocalTime scheduledTime;
    private int stock;
    private String category;
    private String notes;
    private String refillDate;
    private boolean taken;
    private LocalDateTime takenAt;
    private int priority;
    private String timeString;
    
    // Constructor
    public Medicine(String name, String dosage, String time, int stock, String category, String notes, String refillDate) {
        this.name = name;
        this.dosage = dosage;
        this.timeString = time;
        this.stock = stock;
        this.category = category;
        this.notes = notes;
        this.refillDate = refillDate;
        this.taken = false;
        this.takenAt = null;
        
        // Parse time
        try {
            this.scheduledTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            this.scheduledTime = LocalTime.of(8, 0);
        }
        
        this.priority = calculatePriority();
    }
    
    // CO3: Priority calculation for Priority Queue
    private int calculatePriority() {
        int basePriority = 10;
        
        if (stock <= 5) basePriority += 5;
        
        LocalTime now = LocalTime.now();
        if (!taken && scheduledTime.isBefore(now)) {
            basePriority += 10;
        }
        
        if ("blood-pressure".equals(category) || "diabetes".equals(category)) {
            basePriority += 3;
        }
        
        return basePriority;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public LocalTime getScheduledTime() { return scheduledTime; }
    public String getTimeString() { return timeString; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public String getNotes() { return notes; }
    public String getRefillDate() { return refillDate; }
    public boolean isTaken() { return taken; }
    public LocalDateTime getTakenAt() { return takenAt; }
    public int getPriority() { return calculatePriority(); }
    
    // Setters
    public void setStock(int stock) { this.stock = stock; }
    
    // Business logic methods
    public void markAsTaken() {
        this.taken = true;
        this.takenAt = LocalDateTime.now();
        if (stock > 0) stock--;
    }
    
    public void decreaseStock() {
        if (stock > 0) stock--;
    }
    
    public boolean isLowStock() {
        return stock > 0 && stock <= 5;
    }
    
    public boolean isMissed() {
        LocalTime now = LocalTime.now();
        return !taken && scheduledTime.isBefore(now);
    }
    
    public boolean needsRefill() {
        if (refillDate == null || refillDate.isEmpty()) return false;
        try {
            LocalDate refill = LocalDate.parse(refillDate);
            return !refill.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
    
    // CO1: Comparable implementation for sorting
    @Override
    public int compareTo(Medicine other) {
        return this.name.compareToIgnoreCase(other.name);
    }
    
    public int compareByTime(Medicine other) {
        return this.scheduledTime.compareTo(other.scheduledTime);
    }
    
    public int compareByPriority(Medicine other) {
        return Integer.compare(other.getPriority(), this.getPriority());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s | %-10s | %s | Stock: %-3d", 
            name, dosage, getTimeString(), stock));
        
        if (taken) {
            sb.append(" | ✅ TAKEN");
            if (takenAt != null) {
                sb.append(" at ").append(takenAt.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        } else if (isMissed()) {
            sb.append(" | ⚠️  MISSED");
        } else {
            sb.append(" | ⏳ PENDING");
        }
        
        if (isLowStock()) sb.append(" | 🔴 LOW STOCK");
        if (needsRefill()) sb.append(" | 🔔 REFILL NEEDED");
        
        if (category != null && !category.isEmpty()) {
            sb.append("\n    Category: ").append(category);
        }
        
        if (notes != null && !notes.isEmpty()) {
            sb.append("\n    Notes: ").append(notes);
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medicine medicine = (Medicine) obj;
        return name.equalsIgnoreCase(medicine.name);
    }
    
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

// ============================================================================
// CO2: NODE CLASS FOR SINGLY LINKED LIST
// ============================================================================
class MedicineNode {
    Medicine data;
    MedicineNode next;
    
    public MedicineNode(Medicine data) {
        this.data = data;
        this.next = null;
    }
}

// ============================================================================
// CO2: EMERGENCY CONTACT CLASS - ADT
// ============================================================================
class EmergencyContact {
    private String name;
    private String phone;
    private String relationship;
    
    public EmergencyContact(String name, String phone, String relationship) {
        this.name = name;
        this.phone = phone;
        this.relationship = relationship;
    }
    
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getRelationship() { return relationship; }
    
    @Override
    public String toString() {
        return String.format("%-20s | %-15s | %s", name, phone, relationship);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmergencyContact contact = (EmergencyContact) obj;
        return name.equalsIgnoreCase(contact.name);
    }
    
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

// ============================================================================
// CO2: NODE CLASS FOR DOUBLY LINKED LIST
// ============================================================================
class EmergencyContactNode {
    EmergencyContact data;
    EmergencyContactNode next;
    EmergencyContactNode prev;
    
    public EmergencyContactNode(EmergencyContact data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// ============================================================================
// MEDICINE MANAGER - Core DSA Operations (CO1, CO2, CO3, CO4)
// ============================================================================
class MedicineManager {
    // CO2: Singly Linked List for medicines
    private MedicineNode head;
    private int size;
    
    // CO4: HashMap for O(1) lookup
    private HashMap<String, Medicine> medicineMap;
    
    // CO3: Stack for undo operations
    private Stack<Medicine> undoStack;
    private Stack<Medicine> redoStack;
    
    // CO3: Queue for scheduled processing
    private Queue<Medicine> medicineQueue;
    
    // CO3: Priority Queue for urgent medicines
    private PriorityQueue<Medicine> priorityQueue;
    
    // CO3: Deque for bidirectional operations
    private Deque<Medicine> medicineDeque;
    
    // CO1: ArrayList for sorting operations
    private ArrayList<Medicine> sortableList;
    
    // CO4: Interaction warnings database
    private HashMap<String, List<String>> interactionWarnings;
    
    // Constructor
    public MedicineManager() {
        this.head = null;
        this.size = 0;
        this.medicineMap = new HashMap<>();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.medicineQueue = new LinkedList<>();
        this.priorityQueue = new PriorityQueue<>((a, b) -> 
            Integer.compare(b.getPriority(), a.getPriority())
        );
        this.medicineDeque = new ArrayDeque<>();
        this.sortableList = new ArrayList<>();
        
        initializeInteractionWarnings();
    }
    
    // CO4: Initialize interaction warnings HashMap
    private void initializeInteractionWarnings() {
        interactionWarnings = new HashMap<>();
        interactionWarnings.put("warfarin", Arrays.asList("aspirin", "ibuprofen"));
        interactionWarnings.put("aspirin", Arrays.asList("warfarin", "ibuprofen"));
        interactionWarnings.put("ibuprofen", Arrays.asList("aspirin", "warfarin"));
        interactionWarnings.put("metformin", Arrays.asList("alcohol"));
        interactionWarnings.put("lisinopril", Arrays.asList("potassium"));
    }
    
    // ========================================================================
    // CO2: INSERT OPERATION - Singly Linked List - O(n)
    // ========================================================================
    public void addMedicine(Medicine medicine) {
        // Add to linked list
        MedicineNode newNode = new MedicineNode(medicine);
        if (head == null) {
            head = newNode;
        } else {
            MedicineNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        
        // CO4: Add to HashMap for O(1) lookup
        medicineMap.put(medicine.getName().toLowerCase(), medicine);
        
        // CO3: Add to all queue structures
        priorityQueue.offer(medicine);
        medicineQueue.offer(medicine);
        medicineDeque.offerLast(medicine);
        
        // CO1: Add to sortable list
        sortableList.add(medicine);
        
        // CO3: Push to undo stack
        undoStack.push(medicine);
        redoStack.clear(); // Clear redo stack on new action
    }
    
    // ========================================================================
    // CO2: DELETE OPERATION - O(n)
    // ========================================================================
    public boolean deleteMedicine(String name) {
        if (head == null) return false;
        
        // Delete from head
        if (head.data.getName().equalsIgnoreCase(name)) {
            Medicine removed = head.data;
            head = head.next;
            size--;
            removeFromAllStructures(removed);
            return true;
        }
        
        // Delete from middle/end
        MedicineNode current = head;
        while (current.next != null) {
            if (current.next.data.getName().equalsIgnoreCase(name)) {
                Medicine removed = current.next.data;
                current.next = current.next.next;
                size--;
                removeFromAllStructures(removed);
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    private void removeFromAllStructures(Medicine medicine) {
        medicineMap.remove(medicine.getName().toLowerCase());
        priorityQueue.remove(medicine);
        medicineQueue.remove(medicine);
        medicineDeque.remove(medicine);
        sortableList.remove(medicine);
    }
    
    // ========================================================================
    // CO4: HASH-BASED OPERATIONS - O(1) average
    // ========================================================================
    public boolean markAsTaken(String name) {
        Medicine medicine = medicineMap.get(name.toLowerCase());
        if (medicine != null) {
            medicine.markAsTaken();
            // Update priority queue
            priorityQueue.remove(medicine);
            priorityQueue.offer(medicine);
            return true;
        }
        return false;
    }
    
    public boolean updateStock(String name, int newStock) {
        Medicine medicine = medicineMap.get(name.toLowerCase());
        if (medicine != null) {
            medicine.setStock(newStock);
            return true;
        }
        return false;
    }
    
    // ========================================================================
    // CO1: LINEAR SEARCH - O(n) Time, O(1) Space
    // ========================================================================
    public Medicine linearSearch(String name) {
        System.out.println("\n🔍 PERFORMING LINEAR SEARCH...");
        System.out.println("Algorithm: Sequential search through linked list");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");
        
        MedicineNode current = head;
        int comparisons = 0;
        
        while (current != null) {
            comparisons++;
            if (current.data.getName().equalsIgnoreCase(name)) {
                System.out.println("✅ Found after " + comparisons + " comparisons");
                return current.data;
            }
            current = current.next;
        }
        
        System.out.println("❌ Not found after " + comparisons + " comparisons");
        return null;
    }
    
    // ========================================================================
    // CO1: BINARY SEARCH - O(log n) Time, requires sorted array
    // ========================================================================
    public Medicine binarySearch(String name) {
        System.out.println("\n🔍 PERFORMING BINARY SEARCH...");
        System.out.println("Algorithm: Divide and conquer search");
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Prerequisite: List must be sorted");
        
        // Sort the list first
        Collections.sort(sortableList);
        
        int left = 0;
        int right = sortableList.size() - 1;
        int comparisons = 0;
        
        while (left <= right) {
            comparisons++;
            int mid = left + (right - left) / 2;
            Medicine midMed = sortableList.get(mid);
            
            int cmp = midMed.getName().compareToIgnoreCase(name);
            
            if (cmp == 0) {
                System.out.println("✅ Found after " + comparisons + " comparisons");
                return midMed;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println("❌ Not found after " + comparisons + " comparisons");
        return null;
    }
    
    // ========================================================================
    // CO1: MERGE SORT - O(n log n), Stable
    // ========================================================================
    public void sortByNameMergeSort() {
        System.out.println("\n📊 MERGE SORT BY NAME");
        System.out.println("═════════════════════════════════════");
        System.out.println("Time Complexity: O(n log n) - Best, Average, Worst");
        System.out.println("Space Complexity: O(n)");
        System.out.println("Stability: Stable (preserves order of equal elements)");
        System.out.println("Method: Divide and Conquer\n");
        
        long startTime = System.nanoTime();
        mergeSortByName(sortableList, 0, sortableList.size() - 1);
        long endTime = System.nanoTime();
        
        System.out.println("✅ Sorted " + sortableList.size() + " medicines");
        System.out.println("⏱️  Time taken: " + (endTime - startTime) + " ns");
        displayAllMedicines();
    }
    
    private void mergeSortByName(ArrayList<Medicine> list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortByName(list, left, mid);
            mergeSortByName(list, mid + 1, right);
            
            mergeByName(list, left, mid, right);
        }
    }
    
    private void mergeByName(ArrayList<Medicine> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        ArrayList<Medicine> leftList = new ArrayList<>();
        ArrayList<Medicine> rightList = new ArrayList<>();
        
        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(mid + 1 + j));
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }
        
        while (i < n1) {
            list.set(k++, leftList.get(i++));
        }
        
        while (j < n2) {
            list.set(k++, rightList.get(j++));
        }
    }
    
    // ========================================================================
    // CO1: QUICK SORT - O(n log n) average
    // ========================================================================
    public void sortByTimeQuickSort() {
        System.out.println("\n📊 QUICK SORT BY TIME");
        System.out.println("═════════════════════════════════════");
        System.out.println("Time Complexity: O(n log n) average, O(n²) worst");
        System.out.println("Space Complexity: O(log n)");
        System.out.println("Stability: Unstable");
        System.out.println("Method: Divide and Conquer with Pivot\n");
        
        long startTime = System.nanoTime();
        quickSortByTime(sortableList, 0, sortableList.size() - 1);
        long endTime = System.nanoTime();
        
        System.out.println("✅ Sorted " + sortableList.size() + " medicines");
        System.out.println("⏱️  Time taken: " + (endTime - startTime) + " ns");
        displayAllMedicines();
    }
    
    private void quickSortByTime(ArrayList<Medicine> list, int low, int high) {
        if (low < high) {
            int pi = partitionByTime(list, low, high);
            quickSortByTime(list, low, pi - 1);
            quickSortByTime(list, pi + 1, high);
        }
    }
    
    private int partitionByTime(ArrayList<Medicine> list, int low, int high) {
        Medicine pivot = list.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (list.get(j).compareByTime(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
    
    // ========================================================================
    // CO1: BUBBLE SORT - O(n²)
    // ========================================================================
    public void bubbleSortDemo() {
        System.out.println("\n📊 BUBBLE SORT DEMONSTRATION");
        System.out.println("═════════════════════════════════════");
        System.out.println("Time Complexity: O(n²)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Stability: Stable\n");
        
        ArrayList<Medicine> temp = new ArrayList<>(sortableList);
        long startTime = System.nanoTime();
        
        int n = temp.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (temp.get(j).compareTo(temp.get(j + 1)) > 0) {
                    Collections.swap(temp, j, j + 1);
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("✅ Sorted in " + (endTime - startTime) + " ns");
        System.out.println("Note: Bubble sort is inefficient for large datasets");
    }
    
    // ========================================================================
    // CO1: SELECTION SORT - O(n²)
    // ========================================================================
    public void selectionSortDemo() {
        System.out.println("\n📊 SELECTION SORT DEMONSTRATION");
        System.out.println("═════════════════════════════════════");
        System.out.println("Time Complexity: O(n²)");
        System.out.println("Space Complexity: O(1)");
        System.out.println("Stability: Unstable\n");
        
        ArrayList<Medicine> temp = new ArrayList<>(sortableList);
        long startTime = System.nanoTime();
        
        int n = temp.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (temp.get(j).compareTo(temp.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(temp, i, minIdx);
        }
        
        long endTime = System.nanoTime();
        System.out.println("✅ Sorted in " + (endTime - startTime) + " ns");
    }
    
    // ========================================================================
    // CO1: COMPARE ALL SORTING ALGORITHMS
    // ========================================================================
    public void compareAllSorts() {
        System.out.println("\n⚖️  SORTING ALGORITHM COMPARISON");
        System.out.println("═══════════════════════════════════════════════════════");
        
        ArrayList<Medicine> temp1 = new ArrayList<>(sortableList);
        ArrayList<Medicine> temp2 = new ArrayList<>(sortableList);
        ArrayList<Medicine> temp3 = new ArrayList<>(sortableList);
        
        // Bubble Sort
        long start = System.nanoTime();
        for (int i = 0; i < temp1.size() - 1; i++) {
            for (int j = 0; j < temp1.size() - i - 1; j++) {
                if (temp1.get(j).compareTo(temp1.get(j + 1)) > 0) {
                    Collections.swap(temp1, j, j + 1);
                }
            }
        }
        long bubbleTime = System.nanoTime() - start;
        
        // Selection Sort
        start = System.nanoTime();
        for (int i = 0; i < temp2.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < temp2.size(); j++) {
                if (temp2.get(j).compareTo(temp2.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(temp2, i, minIdx);
        }
        long selectionTime = System.nanoTime() - start;
        
        // Merge Sort
        start = System.nanoTime();
        mergeSortByName(temp3, 0, temp3.size() - 1);
        long mergeTime = System.nanoTime() - start;
        
        System.out.printf("%-20s | %15s\n", "Algorithm", "Time (ns)");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.printf("%-20s | %15d\n", "Bubble Sort O(n²)", bubbleTime);
        System.out.printf("%-20s | %15d\n", "Selection Sort O(n²)", selectionTime);
        System.out.printf("%-20s | %15d\n", "Merge Sort O(n log n)", mergeTime);
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Winner: " + (mergeTime < bubbleTime && mergeTime < selectionTime ? "Merge Sort" : "N/A"));
    }
    
    // ========================================================================
    // CO2: REVERSE LINKED LIST - O(n)
    // ========================================================================
    public void reverseLinkedList() {
        System.out.println("\n🔄 REVERSING LINKED LIST");
        System.out.println("═════════════════════════════════════");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)\n");
        
        MedicineNode prev = null;
        MedicineNode current = head;
        MedicineNode next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
        System.out.println("✅ List reversed successfully!");
        displayAllMedicines();
    }
    
    // ========================================================================
    // CO2: DETECT DUPLICATE TIMES
    // ========================================================================
    public void detectDuplicateTimes() {
        System.out.println("\n🔍 DETECTING DUPLICATE SCHEDULED TIMES");
        System.out.println("═════════════════════════════════════════");
        
        HashMap<String, List<String>> timeMap = new HashMap<>();
        MedicineNode current = head;
        
        while (current != null) {
            String time = current.data.getTimeString();
            timeMap.putIfAbsent(time, new ArrayList<>());
            timeMap.get(time).add(current.data.getName());
            current = current.next;
        }
        
        boolean foundDuplicates = false;
        for (Map.Entry<String, List<String>> entry : timeMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                foundDuplicates = true;
                System.out.println("⚠️  Time " + entry.getKey() + ": " + entry.getValue());
            }
        }
        
        if (!foundDuplicates) {
            System.out.println("✅ No duplicate times found");
        }
    }
    
    // ========================================================================
    // CO3: STACK OPERATIONS - Undo/Redo
    // ========================================================================
    public void demonstrateUndoRedo() {
        System.out.println("\n↩️  STACK-BASED UNDO/REDO DEMONSTRATION");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Undo Stack Size: " + undoStack.size());
        System.out.println("Redo Stack Size: " + redoStack.size());
        
        if (!undoStack.isEmpty()) {
            Medicine lastAdded = undoStack.pop();
            redoStack.push(lastAdded);
            System.out.println("\n↩️  Undoing: " + lastAdded.getName());
            System.out.println("✅ Can now redo this operation");
            System.out.println("Remaining in undo stack: " + undoStack.size());
        } else {
            System.out.println("\n❌ Nothing to undo!");
        }
        
        if (!redoStack.isEmpty()) {
            System.out.println("\n💡 Tip: You can redo the last undone operation");
        }
    }
    
    // ========================================================================
    // CO3: QUEUE OPERATIONS
    // ========================================================================
    public void displayMedicineQueue() {
        System.out.println("\n📋 MEDICINE QUEUE (FIFO - First In First Out)");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Queue Size: " + medicineQueue.size());
        System.out.println();
        
        int position = 1;
        for (Medicine med : medicineQueue) {
            System.out.printf("%d. %-20s at %s\n", position++, med.getName(), med.getTimeString());
        }
    }
    
    // ========================================================================
    // CO3: CIRCULAR QUEUE DEMONSTRATION
    // ========================================================================
    public void circularQueueDemo() {
        System.out.println("\n🔄 CIRCULAR QUEUE DEMONSTRATION");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Concept: Fixed-size queue with circular wrapping");
        System.out.println("Use Case: Daily medication schedule (repeats)\n");
        
        // Simulate circular queue with array
        int capacity = 5;
        Medicine[] circularQueue = new Medicine[capacity];
        int front = 0, rear = 0, count = 0;
        
        System.out.println("Adding medicines to circular queue:");
        MedicineNode current = head;
        int added = 0;
        
        while (current != null && count < capacity) {
            circularQueue[rear] = current.data;
            rear = (rear + 1) % capacity;
            count++;
            System.out.println("✅ Added: " + current.data.getName());
            current = current.next;
            added++;
        }
        
        System.out.println("\nCircular Queue Status:");
        System.out.println("Capacity: " + capacity);
        System.out.println("Current Size: " + count);
        System.out.println("Front Index: " + front);
        System.out.println("Rear Index: " + rear);
    }
    
    // ========================================================================
    // CO3: DEQUE OPERATIONS
    // ========================================================================
    public void dequeDemo() {
        System.out.println("\n↔️  DEQUE (Double-Ended Queue) DEMONSTRATION");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Operations: Add/Remove from both ends\n");
        
        System.out.println("Current Deque Size: " + medicineDeque.size());
        
        if (!medicineDeque.isEmpty()) {
            System.out.println("\nFront element: " + medicineDeque.peekFirst().getName());
            System.out.println("Rear element: " + medicineDeque.peekLast().getName());
            
            System.out.println("\nOperations available:");
            System.out.println("• offerFirst() - Add to front");
            System.out.println("• offerLast() - Add to rear");
            System.out.println("• pollFirst() - Remove from front");
            System.out.println("• pollLast() - Remove from rear");
        }
    }
    
    // ========================================================================
    // CO3: PRIORITY QUEUE
    // ========================================================================
    public void displayPriorityQueue() {
        System.out.println("\n🔝 PRIORITY QUEUE (Heap-Based)");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Highest priority medicines processed first");
        System.out.println("Based on: Stock level + Missed status + Category\n");
        
        PriorityQueue<Medicine> tempQueue = new PriorityQueue<>(priorityQueue);
        int position = 1;
        
        System.out.printf("%-5s %-20s %-10s\n", "Pos", "Medicine", "Priority");
        System.out.println("─────────────────────────────────────────");
        
        while (!tempQueue.isEmpty()) {
            Medicine med = tempQueue.poll();
            System.out.printf("%-5d %-20s %-10d\n", position++, med.getName(), med.getPriority());
        }
    }
    
    // ========================================================================
    // CO4: HASHMAP COLLISION DEMONSTRATION
    // ========================================================================
    public void hashMapCollisionDemo() {
        System.out.println("\n#️⃣  HASHMAP COLLISION DEMONSTRATION");
        System.out.println("═════════════════════════════════════════");
        System.out.println("Showing hash codes and potential collisions\n");
        
        System.out.printf("%-20s | %-15s\n", "Medicine Name", "Hash Code");
        System.out.println("─────────────────────────────────────────");
        
        MedicineNode current = head;
        while (current != null) {
            String name = current.data.getName();
            int hashCode = name.toLowerCase().hashCode();
            System.out.printf("%-20s | %-15d\n", name, hashCode);
            current = current.next;
        }
        
        System.out.println("\nCollision Handling: Java HashMap uses chaining");
        System.out.println("Time Complexity: O(1) average, O(n) worst case");
    }
    
    // ========================================================================
    // CO4: CHECK INTERACTION WARNINGS
    // ========================================================================
    public void checkInteractionWarnings() {
        System.out.println("\n⚠️  MEDICINE INTERACTION WARNINGS");
        System.out.println("═════════════════════════════════════════");
        
        List<String> activeMedicines = new ArrayList<>();
        MedicineNode current = head;
        
        while (current != null) {
            if (!current.data.isTaken()) {
                activeMedicines.add(current.data.getName().toLowerCase());
            }
            current = current.next;
        }
        
        boolean warningsFound = false;
        for (String med : activeMedicines) {
            if (interactionWarnings.containsKey(med)) {
                for (String interacts : interactionWarnings.get(med)) {
                    if (activeMedicines.contains(interacts)) {
                        System.out.println("⚠️  " + med.toUpperCase() + 
                            " may interact with " + interacts.toUpperCase());
                        warningsFound = true;
                    }
                }
            }
        }
        
        if (!warningsFound) {
            System.out.println("✅ No interactions detected among active medicines");
        }
    }
    
    // ========================================================================
    // DISPLAY AND REPORTING METHODS
    // ========================================================================
    
    public void displayAllMedicines() {
        System.out.println("\n📋 ALL MEDICINES");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        if (head == null) {
            System.out.println("No medicines added yet.");
            return;
        }
        
        MedicineNode current = head;
        int count = 1;
        
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
        
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Total: " + size + " medicines");
    }
    
    public void displayDailySummary() {
        int total = 0, taken = 0, pending = 0, missed = 0;
        
        MedicineNode current = head;
        while (current != null) {
            total++;
            if (current.data.isTaken()) {
                taken++;
            } else if (current.data.isMissed()) {
                missed++;
            } else {
                pending++;
            }
            current = current.next;
        }
        
        System.out.println("\n📊 DAILY SUMMARY");
        System.out.println("═══════════════════════════════════");
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║ Total Medicines: " + String.format("%-12d", total) + "║");
        System.out.println("║ ✅ Taken:        " + String.format("%-12d", taken) + "║");
        System.out.println("║ ⏳ Pending:      " + String.format("%-12d", pending) + "║");
        System.out.println("║ ⚠️  Missed:       " + String.format("%-12d", missed) + "║");
        System.out.println("╚═══════════════════════════════╝");
    }
    
    public void displayAdherenceRate() {
        int total = 0, taken = 0;
        
        MedicineNode current = head;
        while (current != null) {
            total++;
            if (current.data.isTaken()) {
                taken++;
            }
            current = current.next;
        }
        
        double rate = total > 0 ? (taken * 100.0 / total) : 0;
        
        System.out.println("\n📈 ADHERENCE RATE");
        System.out.println("═══════════════════════════════");
        System.out.println("Rate: " + String.format("%.1f%%", rate));
        System.out.println("Taken: " + taken + " / " + total);
        
        if (rate >= 90) {
            System.out.println("Status: 🌟 Excellent!");
        } else if (rate >= 75) {
            System.out.println("Status: ✅ Good");
        } else if (rate >= 50) {
            System.out.println("Status: ⚠️  Needs Improvement");
        } else {
            System.out.println("Status: ❌ Poor - Please consult doctor");
        }
    }
    
    public void displayLowStockAlert() {
        System.out.println("\n🔴 LOW STOCK ALERT");
        System.out.println("═══════════════════════════════");
        
        boolean foundLowStock = false;
        MedicineNode current = head;
        
        while (current != null) {
            if (current.data.isLowStock()) {
                System.out.println("⚠️  " + current.data.getName() + 
                    " - Only " + current.data.getStock() + " left");
                foundLowStock = true;
            }
            current = current.next;
        }
        
        if (!foundLowStock) {
            System.out.println("✅ All medicines adequately stocked");
        }
    }
    
    public void displayRefillReminders() {
        System.out.println("\n🔔 REFILL REMINDERS");
        System.out.println("═══════════════════════════════");
        
        boolean foundRefills = false;
        MedicineNode current = head;
        
        while (current != null) {
            if (current.data.needsRefill()) {
                System.out.println("🔔 " + current.data.getName() + 
                    " - Refill date: " + current.data.getRefillDate());
                foundRefills = true;
            }
            current = current.next;
        }
        
        if (!foundRefills) {
            System.out.println("✅ No refills needed currently");
        }
    }
    
    public void displayCategoryReport() {
        System.out.println("\n📑 MEDICINES BY CATEGORY");
        System.out.println("═══════════════════════════════");
        
        HashMap<String, List<String>> categoryMap = new HashMap<>();
        MedicineNode current = head;
        
        while (current != null) {
            String category = current.data.getCategory();
            if (category == null || category.isEmpty()) {
                category = "Uncategorized";
            }
            
            categoryMap.putIfAbsent(category, new ArrayList<>());
            categoryMap.get(category).add(current.data.getName());
            current = current.next;
        }
        
        for (Map.Entry<String, List<String>> entry : categoryMap.entrySet()) {
            System.out.println("\n" + entry.getKey().toUpperCase() + " (" + entry.getValue().size() + "):");
            for (String medName : entry.getValue()) {
                System.out.println("  • " + medName);
            }
        }
    }
    
    public void exportDataSimulation() {
        System.out.println("\n📥 EXPORTING DATA (Simulation)");
        System.out.println("═══════════════════════════════");
        System.out.println("Format: CSV");
        System.out.println("Filename: medicines_export.csv\n");
        
        System.out.println("Name,Dosage,Time,Stock,Taken,Category");
        System.out.println("─────────────────────────────────────────");
        
        MedicineNode current = head;
        while (current != null) {
            Medicine med = current.data;
            System.out.printf("%s,%s,%s,%d,%s,%s\n",
                med.getName(),
                med.getDosage(),
                med.getTimeString(),
                med.getStock(),
                med.isTaken() ? "Yes" : "No",
                med.getCategory() != null ? med.getCategory() : "N/A"
            );
            current = current.next;
        }
        
        System.out.println("\n✅ Export complete!");
    }
    
    // CO1: Complexity Analysis
    public void analyzeComplexity() {
        System.out.println("\n📊 TIME & SPACE COMPLEXITY ANALYSIS");
        System.out.println("═════════════════════════════════════════════════════════════");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Operation", "Time", "Space", "Data Structure");
        System.out.println("═════════════════════════════════════════════════════════════");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Add Medicine", "O(n)", "O(1)", "Singly Linked List");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Search by Name", "O(1) avg", "O(1)", "HashMap");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Linear Search", "O(n)", "O(1)", "Linked List");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Binary Search", "O(log n)", "O(1)", "Sorted ArrayList");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Merge Sort", "O(n log n)", "O(n)", "ArrayList");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Quick Sort", "O(n log n)", "O(log n)", "ArrayList");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Bubble Sort", "O(n²)", "O(1)", "ArrayList");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Reverse List", "O(n)", "O(1)", "Linked List");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Priority Queue Ops", "O(log n)", "O(1)", "Heap");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Stack Push/Pop", "O(1)", "O(1)", "Stack");
        System.out.printf("%-25s | %-12s | %-12s | %s\n", 
            "Queue Enqueue/Dequeue", "O(1)", "O(1)", "Queue");
        System.out.println("═════════════════════════════════════════════════════════════");
    }
}

// ============================================================================
// EMERGENCY CONTACT MANAGER - CO2: Doubly Linked List
// ============================================================================
class EmergencyContactManager {
    private EmergencyContactNode head;
    private EmergencyContactNode tail;
    private int size;
    
    // CO4: HashMap for O(1) search
    private HashMap<String, EmergencyContact> contactMap;
    
    public EmergencyContactManager() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.contactMap = new HashMap<>();
    }
    
    // CO2: Insert in Doubly Linked List - O(1) with tail pointer
    public void addContact(EmergencyContact contact) {
        EmergencyContactNode newNode = new EmergencyContactNode(contact);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        contactMap.put(contact.getName().toLowerCase(), contact);
        size++;
    }
    
    // CO2: Delete from Doubly Linked List - O(n) search, O(1) delete
    public boolean removeContact(String name) {
        EmergencyContactNode current = head;
        
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                
                contactMap.remove(name.toLowerCase());
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    // CO4: Hash-based search - O(1)
    public EmergencyContact searchContact(String name) {
        return contactMap.get(name.toLowerCase());
    }
    
    // CO2: Forward traversal
    public void displayAllContacts() {
        System.out.println("\n🚨 EMERGENCY CONTACTS (Doubly Linked List)");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.printf("%-20s | %-15s | %s\n", "Name", "Phone", "Relationship");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        if (head == null) {
            System.out.println("No emergency contacts added yet.");
            return;
        }
        
        EmergencyContactNode current = head;
        int count = 1;
        
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
        
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("Total: " + size + " contacts");
    }
    
    // CO2: Backward traversal (unique to doubly linked list)
    public void displayContactsReverse() {
        System.out.println("\n🔄 EMERGENCY CONTACTS (Reverse Order)");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        if (tail == null) {
            System.out.println("No emergency contacts added yet.");
            return;
        }
        
        EmergencyContactNode current = tail;
        int count = size;
        
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.prev;
            count--;
        }
        
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("Demonstrates bidirectional traversal capability");
    }
}