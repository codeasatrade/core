import "./App.css";

import Challenges from "./Challenges";
import ChallengeDetail from "./ChallengeDetail";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Challenges />} />
        <Route path="/challenges" element={<Challenges />} />
        <Route path="/challenge/:url" element={<ChallengeDetail />} />
      </Routes>
    </Router>
  );
};

export default App;
