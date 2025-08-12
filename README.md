Binary Search Tree (BST) – Spring Boot + React
A minimal full-stack project that lets users enter numbers, builds a Binary Search Tree (BST), shows the result as JSON, and stores previous trees in a MySQL database. A simple React UI toggles between the latest result and previously saved trees.

Features
1. Enter a list of numbers (comma/space separated) and build a BST.
2. See the tree as nested JSON { value, left, right }.
3. Save each build to the DB; list “Previous Trees”.
4. Prevents accidental duplicates on the frontend.
5. CORS-enabled for local React development.

API
    POST /buildTree
    [8, 4, 12, 2, 6, 10, 14]
    
    Response (JSON):
    json
    {
      "id": 1,
      "value": 8,
      "left": { "value": 4, "left": { "value": 2 }, "right": { "value": 6 } },
      "right": { "value": 12, "left": { "value": 10 }, "right": { "value": 14 } }
    }

    POST /buildTreeFromString
    8, 4 12 2 6 10 14

   GET /getPreviousTrees
   Response (JSON):
    
    json
    [
      {
        "id": 7,
        "treeJson": "{\"value\":8,\"left\":{...},\"right\":{...}}",
        "userInputs": "[8,4,12,2,6,10,14]"
      }
    ]


Database
Create a schema (e.g., binarytree_db_2024) and update credentials.

Frontend Setup
Install & run
cd DSA-final-sprint-semester_04_SD-12_MH(frontend_folder)
npm install
npm start
# app: http://localhost:3000

Run tests:
mvn test
