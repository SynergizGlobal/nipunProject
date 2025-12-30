import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
	const [email, setEmail] = useState("");
	const [mobile, setMobile] = useState("");
	const [message, setMessage] = useState("");

	const navigate = useNavigate();

	const handleLogin = async () => {
		try {
			const res = await axios.post(
				"http://localhost:8080/api/contractor/send-otp",
				{ email, mobile }
			);

			const status = res.data.status;

			if (status?.toLowerCase().includes("otp sent")) {
				// OTP sent successfully, redirect
				navigate("/verify-otp", { state: { mobile } });
				return;
			}

			if (status?.toLowerCase().includes("not found")) {
				setMessage("Mobile not registered. Please register.");
				return;
			}

			setMessage(status);


		} catch (err) {
			setMessage(err.response?.data?.message || "Login failed");
		}
	};

	// 👉 Manual registration button
	const openRegister = () => {
		navigate("/register", {
			state: { mobile, email }
		});
	};

	return (
		<div className="login-container">
			<h2>Contractor Login</h2>

			<input
				type="email"
				placeholder="Email"
				value={email}
				onChange={e => setEmail(e.target.value)}
			/>

			<input
				type="text"
				placeholder="Mobile Number"
				value={mobile}
				maxLength="10"
				onChange={e => setMobile(e.target.value.replace(/\D/g, ""))}
			/>

			<button onClick={handleLogin}>Send OTP</button>

			{message && <p className="login-message">{message}</p>}

			{/* 👇 Registration link */}
			<p style={{ marginTop: "15px" }}>
				New Contractor?{" "}
				<span
					onClick={openRegister}
					style={{
						color: "#007bff",
						cursor: "pointer",
						fontWeight: "bold"
					}}
				>
					Register here
				</span>
			</p>
		</div>
	);
}

export default Login;
