import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/Login/Login"; // matches Login.jsx
import OtpVerify from "./components/OtpVerify/OtpVerify";
import Register from "./components/Register/Register";
import Dashboard from "./components/Dashboard/Dashboard";



function App() {
	return (
		<Router basename="/nipun">
			<Routes>
				<Route path="/" element={<Navigate to="/login" />} />
				<Route path="/login" element={<Login />} />
				<Route path="/verify-otp" element={<OtpVerify />} />
				<Route path="/register" element={<Register />} />
				<Route path="/dashboard" element={<Dashboard />} />

			</Routes>
		</Router>
	);
}

export default App;
