# Lumina Project: 5-Member Work Breakdown, Git Strategy & Code Review Guide

> **Project Name:** SL Solutions - Service & Inventory Management Portal (Lumina)  
> **Architecture:** Spring Boot 3.x (Java 17) + React 19 / Vite + Tailwind CSS + PostgreSQL  
> **Code Modularization Status:** All components refactored into micro-components (< 50-60 lines each). 0 lint warnings, 0 build errors.

---

## 1. Team Responsibilities Breakdown (Members 1 - 5)

Each team member is assigned a dedicated functional domain and isolated files to prevent merge conflicts.

```
┌────────────────────────────────────────────────────────────────────────┐
│                        PROJECT MANAGER / MAIN REPO                     │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
       ┌──────────────┬─────────────┼─────────────┬──────────────┐
       │              │             │             │              │
   Member 1       Member 2      Member 3      Member 4       Member 5
  (Auth & Client) (Technician)   (Admin)    (Super Admin &  (Backend & DB)
                                              Layout UI)
```

---

### Member 1: Authentication & Client Portal
- **Git Branch:** `feature/auth-and-client-portal`
- **Assigned Frontend Files:**
  - `src/pages/auth/LoginPage.tsx`
  - `src/components/auth/LoginForm.tsx`
  - `src/components/auth/form/LoginFormInputs.tsx`
  - `src/components/auth/LoginHero.tsx`
  - `src/components/auth/hero/LoginHeroBackground.tsx`
  - `src/components/auth/hero/LoginHeroFeatureCards.tsx`
  - `src/pages/client/ClientDashboardPage.tsx`
  - `src/pages/client/MyProductsPage.tsx`
  - `src/pages/client/ClientServiceRequestsPage.tsx`
  - `src/pages/client/ClientRequestProductPage.tsx`
- **Key Responsibilities:**
  - Login authentication flow, JWT handling, password validation, role selector.
  - Client portal dashboard and client service request forms.
- **Git Push Command Example:**
  ```bash
  git checkout -b feature/auth-and-client-portal
  git add src/pages/auth/ src/components/auth/ src/pages/client/
  git commit -m "feat(auth-client): implement login authentication and client portal pages"
  git push origin feature/auth-and-client-portal
  ```
- **Code Review Defense:**
  - *Examiner asks:* "Show me where the login password validation is."
  - *Answer:* Open `src/components/auth/LoginForm.tsx` line 40.
  - *Examiner asks:* "Change the title of the Client dashboard."
  - *Answer:* Open `src/pages/client/ClientDashboardPage.tsx` line 12.

---

### Member 2: Technician Operations Portal
- **Git Branch:** `feature/technician-portal`
- **Assigned Frontend Files:**
  - `src/pages/technician/TechnicianDashboardPage.tsx`
  - `src/pages/technician/TechnicianOngoingWorksPage.tsx`
  - `src/pages/technician/TechRequestProductPage.tsx`
  - `src/pages/technician/TechnicianHistoryOfWorkPage.tsx`
- **Key Responsibilities:**
  - Technician workflow: Viewing assigned tickets, ongoing repair jobs, requesting spare parts from inventory, and checking completed job history.
- **Git Push Command Example:**
  ```bash
  git checkout -b feature/technician-portal
  git add src/pages/technician/
  git commit -m "feat(tech): build technician dashboard, ongoing tasks, and part request pages"
  git push origin feature/technician-portal
  ```
- **Code Review Defense:**
  - *Examiner asks:* "Where does the technician request spare parts or products?"
  - *Answer:* Open `src/pages/technician/TechRequestProductPage.tsx`.

---

### Member 3: Administrator Portal
- **Git Branch:** `feature/admin-portal`
- **Assigned Frontend Files:**
  - `src/pages/admin/AdminDashboardPage.tsx`
  - `src/pages/admin/AdminTicketsPage.tsx`
  - `src/pages/admin/AdminProductRequestsPage.tsx`
  - `src/pages/admin/AdminTechniciansPage.tsx`
  - `src/pages/admin/AdminClientsPage.tsx`
  - `src/pages/admin/AdminProductsPage.tsx`
  - `src/pages/admin/AdminTechnicalReportsPage.tsx`
  - `src/pages/admin/AdminAdminsPage.tsx`
- **Key Responsibilities:**
  - Operational administration: Ticket dispatch, viewing technician allocations, managing client accounts, inventory stock levels, and generating technical service reports.
- **Git Push Command Example:**
  ```bash
  git checkout -b feature/admin-portal
  git add src/pages/admin/
  git commit -m "feat(admin): build administrator operations, ticket dispatch, and client management"
  git push origin feature/admin-portal
  ```
- **Code Review Defense:**
  - *Examiner asks:* "Where do administrators assign tickets to technicians?"
  - *Answer:* Open `src/pages/admin/AdminTicketsPage.tsx`.

---

### Member 4: Super Admin Portal, Global Layout & System Navigation
- **Git Branch:** `feature/superadmin-and-layout`
- **Assigned Frontend Files:**
  - `src/config/navigationConfig.ts`
  - `src/components/layout/Sidebar.tsx`
  - `src/components/layout/sidebar/SidebarBackground.tsx`
  - `src/components/layout/sidebar/SidebarHeader.tsx`
  - `src/components/layout/sidebar/SidebarNavList.tsx`
  - `src/components/layout/sidebar/SidebarFooter.tsx`
  - `src/components/layout/TopHeader.tsx`
  - `src/components/layout/header/HeaderBrand.tsx`
  - `src/components/layout/header/HeaderSearch.tsx`
  - `src/components/layout/header/HeaderNotifications.tsx`
  - `src/components/layout/header/HeaderProfileMenu.tsx`
  - `src/components/layout/DashboardLayout.tsx`
  - `src/routes/AppRoutes.tsx`
  - `src/routes/ProtectedRoute.tsx`
  - `src/pages/super-admin/*`
- **Key Responsibilities:**
  - Role-based routing, layout shells, side menu navigation map, notifications menu, top header, system settings.
- **Git Push Command Example:**
  ```bash
  git checkout -b feature/superadmin-and-layout
  git add src/components/layout/ src/config/navigationConfig.ts src/routes/ src/pages/super-admin/
  git commit -m "feat(layout): implement responsive sidebar, top header, and super admin routes"
  git push origin feature/superadmin-and-layout
  ```
- **Code Review Defense:**
  - *Examiner asks:* "Add a new menu item to the sidebar."
  - *Answer:* Open `src/config/navigationConfig.ts` and add the item in 3 lines.
  - *Examiner asks:* "Change the title in the top navigation bar."
  - *Answer:* Open `src/components/layout/header/HeaderBrand.tsx` line 25.

---

### Member 5: Backend REST API, Database & Security
- **Git Branch:** `feature/backend-api-services`
- **Assigned Backend Files:**
  - `backend/src/main/java/com/lumina/backend/LuminaBackendApplication.java`
  - `backend/src/main/java/com/lumina/backend/config/SecurityConfig.java`
  - `backend/src/main/java/com/lumina/backend/config/DataInitializer.java`
  - `backend/src/main/java/com/lumina/backend/config/CorsConfig.java`
  - `backend/src/main/java/com/lumina/backend/controller/AuthController.java`
  - `backend/src/main/java/com/lumina/backend/controller/TestController.java`
  - `backend/src/main/java/com/lumina/backend/controller/HealthController.java`
  - `backend/src/main/java/com/lumina/backend/model/*`
  - `backend/src/main/java/com/lumina/backend/repository/*`
  - `backend/src/main/java/com/lumina/backend/security/*`
  - `backend/src/main/resources/application.properties`
- **Key Responsibilities:**
  - Spring Boot 3 REST API, JWT filter, password encryption (BCrypt), PostgreSQL database connection & Hibernate JPA, demo database seeding (`DataInitializer`), test endpoints for role permissions.
- **Git Push Command Example:**
  ```bash
  git checkout -b feature/backend-api-services
  git add backend/
  git commit -m "feat(backend): configure Spring Security JWT, role enum, and PostgreSQL seeding"
  git push origin feature/backend-api-services
  ```
- **Code Review Defense:**
  - *Examiner asks:* "Where are the demo usernames and passwords configured?"
  - *Answer:* Open `backend/src/main/java/com/lumina/backend/config/DataInitializer.java`.
  - *Examiner asks:* "Where is the JWT token expiration configured?"
  - *Answer:* Open `backend/src/main/resources/application.properties` line 18.

---

## 2. PC Setup Guide for Each Team Member

Every member must install the following software on their Windows / Mac / Linux laptop:

### Step 1: Install Git
- Download: [https://git-scm.com/downloads](https://git-scm.com/downloads)
- Configure username and email in terminal:
  ```bash
  git config --global user.name "Your Name"
  git config --global user.email "your.email@example.com"
  ```

### Step 2: Install Node.js (v20 or v22 LTS)
- Download: [https://nodejs.org](https://nodejs.org)
- Check in terminal:
  ```bash
  node -v   # Should be v20.x or higher
  npm -v    # Should be 10.x or higher
  ```

### Step 3: Install Java 17 (JDK)
- Download Temurin or Oracle JDK 17: [https://adoptium.net/temurin/releases/?version=17](https://adoptium.net/temurin/releases/?version=17)
- Check in terminal:
  ```bash
  java -version   # Should show openjdk version "17.0.x"
  ```

### Step 4: Install PostgreSQL & pgAdmin 4 (For Member 5 & Backend)
- Download: [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
- Default connection string: `jdbc:postgresql://localhost:5432/lumina_db`

### Step 5: Install VS Code & Recommended Extensions
- Extensions to install:
  1. **Tailwind CSS IntelliSense** (`bradlc.vscode-tailwindcss`)
  2. **ESLint / Oxlint**
  3. **Extension Pack for Java** (Microsoft)
  4. **Spring Boot Extension Pack** (VMware)

---

## 3. Git Repository Setup & Step-by-Step Workflow

### A. Team Leader: Initializing the GitHub Repository
1. Go to [GitHub.com](https://github.com) and click **New Repository**.
2. Name it: `lumina-service-management` (Set to **Private** or **Public**).
3. Do **NOT** check "Add a README" or ".gitignore" on GitHub (we already have them).
4. Run these commands in your project root (`Lumina Software Project`):
   ```bash
   git init
   git add .
   git commit -m "chore: initial commit with modular architecture and zero warnings"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/lumina-service-management.git
   git push -u origin main
   ```
5. Go to GitHub **Settings > Collaborators > Add people** and invite all 4 members using their GitHub usernames.

---

### B. Team Members 1–5: Cloning and Daily Workflow

#### Step 1: Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/lumina-service-management.git
cd lumina-service-management
npm install --prefix frontend
```

#### Step 2: Create your own feature branch
*Never code directly on `main`!*
```bash
# Member 1:
git checkout -b feature/auth-and-client-portal

# Member 2:
git checkout -b feature/technician-portal

# Member 3:
git checkout -b feature/admin-portal

# Member 4:
git checkout -b feature/superadmin-and-layout

# Member 5:
git checkout -b feature/backend-api-services
```

#### Step 3: Make changes, check, and commit
Before committing, always run:
```bash
npm --prefix frontend run lint   # Must show 0 errors
npm --prefix frontend run build  # Must build successfully
```
Then commit:
```bash
git status
git add .
git commit -m "feat(module): descriptive explanation of what was changed"
git push origin your-branch-name
```

#### Step 4: Create a Pull Request (PR) on GitHub
1. Open the GitHub repository page.
2. Click **Compare & pull request**.
3. Team leader reviews and clicks **Merge pull request** into `main`.

#### Step 5: Update your local code with the latest changes from team
```bash
git checkout main
git pull origin main
git checkout your-branch-name
git merge main
```

---

## 4. Code Review Quick Modification Guide (On-the-Spot Changes)

If the examiner asks you to modify code on the spot during review, here is the cheat sheet:

| Examiner Request | Which File to Open | Line / Action |
|:---|:---|:---|
| **"Change the system logo or remove background"** | `src/components/auth/LoginHero.tsx` | Lines 16-24: `src={slSolutionsLogo1}` |
| **"Add a new sidebar link or change an icon"** | `src/config/navigationConfig.ts` | Edit the items array for the specific role |
| **"Change Header Title or Subtitle"** | `src/components/layout/header/HeaderBrand.tsx` | Line 23: `SL Solutions`, Line 26: `Management System` |
| **"Change Login button text or color"** | `src/components/auth/LoginForm.tsx` | Line 106: `<button className="bg-[#0066ff] ...">` |
| **"Add or remove a feature card on login screen"** | `src/components/auth/hero/LoginHeroFeatureCards.tsx` | Lines 4-9: `FEATURES` array |
| **"Change page title or category badge"** | `src/components/common/PageTitleSection.tsx` | Used across all pages with `title`, `badge`, `subtitle` props |
| **"Change seed user accounts or passwords"** | `backend/.../config/DataInitializer.java` | Lines 25-55: edit `client123`, `tech123`, etc. |
| **"Change port or database connection string"** | `backend/src/main/resources/application.properties` | Lines 3 & 6: `server.port=8080`, `spring.datasource.url` |
| **"Allow or change user roles"** | `src/types/auth.ts` & `backend/.../model/ERole.java` | 4 roles: `ROLE_CLIENT`, `ROLE_ADMIN`, `ROLE_TECHNICIAN`, `ROLE_SUPER_ADMIN` |

---

## 5. Verification Commands

Run these to demonstrate system stability during review:
```bash
# 1. Start Frontend Server
npm run dev
# Running at: http://localhost:5173

# 2. Verify Linting (0 warnings, 0 errors)
npm --prefix frontend run lint

# 3. Verify Frontend Production Build
npm --prefix frontend run build

# 4. Verify Backend Compilation (Java 17)
cd backend && ./mvnw test-compile
```
