import logo from "./logo.svg";
import "./App.css";
import ChallengeList from "./components/ChallengeList";
import AddChallenge from "./components/AddChallenge";
import { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  const [challenges, setChallenges] = useState([
    // { id: 1, month: "January", description: "First challenge description" },
    // { id: 2, month: "February", description: "Second challenge description" },
  ]);

  useEffect(() => {
    const fetchChallenges = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/challenges");
        setChallenges(response.data);
      } catch (error) {
        console.error("Error fetching challenges: ", error);
      }
    };
    fetchChallenges();
  }, []);
  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Monthly Challenges</h1>
      <AddChallenge />
      <ChallengeList challenges={challenges} />
    </div>
  );
}

export default App;
