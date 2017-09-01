# sqlite3-manager

## Usage ##

Getting query results in the form of a list:

`List<Map<Object, Object>> result = sqlite3manager.list(query, pathToDatabase);`

For example:

`result = sqlite3manager.list("SELECT id, name FROM myTable;", "jdbc:sqlite:/.../myDatabase.db");`

You can also run queries (i.e. for inserting or deleting data) using the following function:

`sqlite3manager.runQuery(myQueryString, pathToDatabase);`
