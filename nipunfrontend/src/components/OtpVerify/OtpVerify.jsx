import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import "./OtpVerify.css";

function OtpVerify() {
  const [otp, setOtp] = useState("");
  const [message, setMessage] = useState("");
  const [mobile, setMobile] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (location.state?.mobile) {
      setMobile(location.state.mobile);
    } else {
      setMessage("Mobile number not found. Please go back and try again.");
    }
  }, [location.state]);

  const verifyOtp = async () => {
    if (!otp.trim()) {
      setMessage("Please enter the OTP");
      return;
    }
    if (!mobile) {
      setMessage("Mobile number missing");
      return;
    }

    try {
      const res = await axios.post(
        "http://localhost:8080/api/contractor/verify-otp",
        { mobile, otp: otp.trim() }
      );

      setMessage(res.data.message);

      if (res.data.success) {
        navigate("/dashboard");
      }
    } catch (err) {
      setMessage(err.response?.data?.message || "OTP verification failed");
    }
  };

  return (
    <div className="otp-container">
      <h2>Verify OTP</h2>
      <p>Mobile: {mobile || "Not available"}</p>

      <input
        type="text"
        placeholder="Enter OTP"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
      />

      <button onClick={verifyOtp}>Verify OTP</button>

      {message && (
        <p className={`otp-message ${message.includes("successfully") ? "success" : "error"}`}>
          {message}
        </p>
      )}
    </div>
  );
}

export default OtpVerify;
