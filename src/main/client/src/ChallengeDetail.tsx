import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Editor from "@monaco-editor/react";
import { ChallengeProp } from "./Challenges";

interface challengeSubmissionRequestProp {
  userSolution: string;
  challengeId: number;
  codeLang: string;
}

const ChallengeDetail = () => {
  const { url } = useParams<{ url: string }>();
  const [challenge, setChallenge] = useState<ChallengeProp | null>(null);
  const [code, setCode] = useState<string>(Array(20).fill("").join("\n"));
  const [result, setResult] = useState<string | null>(null);
  const [submissionRequest, setSubmissionRequest] =
    useState<challengeSubmissionRequestProp | null>(null);

  console.log("Submission Request: ", submissionRequest);

  useEffect(() => {
    const fetchChallenge = async () => {
      try {
        const response = await axios.get<ChallengeProp>(
          `http://localhost:3001/api/v1/challenge/${url}`
        );
        const chall: ChallengeProp = response.data as ChallengeProp;
        setResult(null);
        setCode(chall.java as string);

        setChallenge(response.data);
      } catch (error) {
        console.error("Error fetching challenge:", error);
      }
    };

    fetchChallenge();
  }, [url]);

  console.log("Challenge: ", challenge);
  const handleSubmit = async () => {
    setSubmissionRequest({
      userSolution: code,
      challengeId: challenge?.id || 0,
      codeLang: "java",
    });

    try {
      const response = await axios.post(
        `http://localhost:3001/api/v1/challenge/submit`,
        {
          userSolution: code,
          challengeId: challenge?.id || 0,
          codeLang: "java",
        }
      );
      // const data = response.data as string;
      console.log("Response: ", response.data);
      setResult(response.data as string);
    } catch (error) {
      console.error("Error submitting code:", error);
      setResult("Error submitting code");
    }
  };

  if (!challenge) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container">
      <h1>{challenge.name}</h1>
      <p>{challenge.description}</p>
      <Editor
        height="400px"
        defaultLanguage="java"
        theme="vs-dark"
        value={code}
        onChange={(value) => setCode(value || "")}
      />

      <br />

      <button onClick={handleSubmit}>Submit</button>
      <br />
      {result && <div>{result}</div>}
    </div>
  );
};

export default ChallengeDetail;
