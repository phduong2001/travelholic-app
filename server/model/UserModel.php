<?php
class UserModel {
    private $conn;

    public function __construct() {
        $db = new Database();
        $this->conn = $db->connect();
    }

    public function login($account, $password) {
        $query = "SELECT * FROM user " .
                "WHERE (username = '$account' OR email = '$account' OR phone = '$account') AND password = '$password'";
        $result = $this->conn->query($query);
        $data = null;
        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $data = array(
                'success' => true,
                'username' => $row['username'],
                'role' => $row['role']
            );
        }
        else
            $data = array('success' => false);
        return $data;
    }

    public function detail($username) {
        $query = "SELECT * FROM user WHERE username = '$username'";
        $result = $this->conn->query($query);
        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $data = array(
                'success' => true,
                'email' => $row['email'] ?? '',
                'phone' => $row['phone'] ?? '',
                'fullname' => $row['fullname'] ?? '',
                'avatar' => $row['avatar'] ?? '',
                'role' => $row['role'] ?? 'general'
            );
        }
        else
            $data = array('success' => false);
        return $data;
    }

    public function signup($username, $email, $phone, $password, $fullname) {
        $username_query = "SELECT * FROM user WHERE username = '$username'";
        $username_result = $this->conn->query($username_query);

        if ($username_result->num_rows == 0) {
            $email_query = "SELECT * FROM user WHERE email = '$email'";
            $email_result = $this->conn->query($email_query);

            if ($email_result->num_rows == 0) {
                $phone_query = "SELECT * FROM user WHERE phone = '$phone'";
                $phone_result = $this->conn->query($phone_query);

                if ($phone_result->num_rows == 0) {
                    $insert_query = "INSERT INTO user (username, email, phone, password, fullname, role, created_at, updated_at) " .
                        "VALUES ('$username', '$email', '$phone', '$password', '$fullname', 'general', NOW(), NOW())";
                    $this->conn->query($insert_query);
                    $data = array(
                        'success' => true,
                        'message' => 'Account created successfully!'
                    );
                }
                else
                    $data = array(
                        'success' => false,
                        'message' => 'Phone existed'
                    );
            }
            else
                $data = array(
                    'success' => false,
                    'message' => 'Email existed!'
                );
        }
        else
            $data = array(
                'success' => false,
                'message' => 'Username existed!'
            );
        return $data;
    }
}