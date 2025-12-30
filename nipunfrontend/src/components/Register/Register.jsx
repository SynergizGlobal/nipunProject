import React, { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

function Register() {
  const location = useLocation();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    companyName: "",
    contactPerson: "",
    mobile: location.state?.mobile || "",
    email: location.state?.email || "",
    address: ""
  });

  const [message, setMessage] = useState("");

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const register = async () => {
    try {
      const res = await axios.post(
        "http://localhost:8080/api/contractor/register",
        form
      );

      alert(res.data.message);
      navigate("/login");

    } catch (err) {
      setMessage(err.response?.data?.message || "Registration failed");
    }
  };

  return (
    <div className="login-container">
      <h2>Contractor Registration</h2>

      <input name="companyName" placeholder="Company Name" onChange={handleChange} />
      <input name="contactPerson" placeholder="Contact Person" onChange={handleChange} />
      <input name="mobile" value={form.mobile} readOnly />
      <input name="email" value={form.email} readOnly />
      <input name="address" placeholder="Address" onChange={handleChange} />

      <button onClick={register}>Register</button>
      {message && <p>{message}</p>}
    </div>
  );
}

export default Register;
