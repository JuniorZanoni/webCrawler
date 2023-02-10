package Models

import Models.Connection

class ModelEmails {

    List<String> getAll() {
        List<String> emails = []
        Connection.sql.query('''SELECT * FROM emails_web_crawler;''') { resultSet ->
            while(resultSet.next()) {
                String email = resultSet.getString('email')
                emails.add(email)
            }
        }
        return emails
    }

    void add(String email) {
        Connection.sql.execute('''INSERT INTO emails_web_crawler (email) VALUES (?);''', [email])
    }

    void remove(String email) {
        Connection.sql.execute('''DELETE FROM emails_web_crawler WHERE email = ?;''', [email])
    }

    void update(String email, String newEmail) {
        Connection.sql.execute('''UPDATE emails_web_crawler SET email = ? WHERE email = ?;''', [newEmail, email])
    }
}


