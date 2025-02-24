import React, { useEffect } from "react";
import axios from "axios";

const ChallengesController: React.FC = () => {
  //   const [challenges, setChallenges] = useState([]);

  useEffect(() => {
    const fetchChallenges = async () => {
      try {
        const response = await axios.get("/challenges");
        console.log("Response data:", response.data); // Log the response data

        // setChallenges(response.data);
      } catch (error) {
        console.error("Error fetching challenges:", error);
      }
    };

    fetchChallenges();
  }, []);

  return (
    <div>
      <h1>Challenges</h1>
      {/* <ul>
        {challenges.map((challenge: any) => (
          <li key={challenge.id}>{challenge.name}</li>
        ))}
      </ul> */}
    </div>
  );
};

export default ChallengesController;
