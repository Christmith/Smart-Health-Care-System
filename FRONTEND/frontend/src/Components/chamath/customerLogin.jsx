import React, { useEffect, useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import './customerLogin.css'; // Importing CSS file
import axios from 'axios';

const CusLogin = () => {
    const nav = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();

        if (validateForm()) {
            hasPageLoaded.current = true;
            setLogVal(prev => !prev);
        }
    };

    const validateForm = () => {
        let errors = {};
        let formIsValid = true;

        // Validate Email
        if (!email) {
            formIsValid = false;
            errors['email'] = 'Please enter your email address.';
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            formIsValid = false;
            errors['email'] = 'Please enter a valid email address.';
        }

        // Validate Password
        if (!password) {
            formIsValid = false;
            errors['password'] = 'Please enter your password.';
        }

        setErrors(errors);
        return formIsValid;
    };

    const useridentify = (user) => {
        if (user) {
            localStorage.setItem('loggedUser', user.cusMail);
            return true;
        }
        return false;
    };

    const [logVal, setLogVal] = useState(false);
    const hasPageLoaded = useRef(false);

    useEffect(() => {
        if (hasPageLoaded.current) {
            axios.post(`http://localhost:5000/customer/getloggeduser`, { mail: email, pass: password })
                .then(res => {
                    if (useridentify(res.data)) {
                        nav('/');
                    } else {
                        alert('Invalid Credentials');
                    }
                });
        }
    }, [logVal]);

    return (
        <div className="login-page">
            <div className="login-container">
                <h4 className="login-header">Customer Login</h4>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className={`login-input border-color ${errors['email'] ? 'is-invalid' : ''}`}
                        />
                        {errors['email'] && (
                            <Form.Text className="text-danger">{errors['email']}</Form.Text>
                        )}
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className={`login-input border-color ${errors['password'] ? 'is-invalid' : ''}`}
                        />
                        {errors['password'] && (
                            <Form.Text className="text-danger">{errors['password']}</Form.Text>
                        )}
                    </Form.Group>

                    <Button variant="primary" type="submit" className="login-btn">
                        Login
                    </Button>
                </Form>
                <p className="mt-3">
                    Don't have an account? <Link to="/cusreg">Create one</Link>
                </p>
            </div>
        </div>

    );
};

export default CusLogin;