// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
import "./App.css";

import Challenges from "./Challenges";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<div>Home</div>} />
          <Route path="/challenges" element={<Challenges />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
