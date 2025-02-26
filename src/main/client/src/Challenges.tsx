import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export interface ChallengeProp {
  id: number;
  name: string;
  description: string;
  endpoint: string;
  java: string;
}

interface PaginatedResponse {
  content: ChallengeProp[];
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
  totalPages: number;
  totalElements: number;
  numberOfElements: number;
}

const Challenges = () => {
  const [challenges, setChallenges] = useState<ChallengeProp[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchChallenges = async (page: number) => {
      try {
        const response = await axios.get<PaginatedResponse>(
          "http://localhost:3001/api/v1/challenges",
          {
            params: { page },
          }
        );
        console.log("Response data:", response.data); // Log the response data

        setChallenges(response.data.content);
        setCurrentPage(response.data.pageable.pageNumber);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching challenges:", error);
      }
    };

    fetchChallenges(currentPage);
  }, [currentPage]);

  const handleNextPage = () => {
    if (currentPage < totalPages - 1) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePreviousPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleRowClick = (endpoint: string) => {
    navigate(`/challenge/${endpoint}`);
  };

  return (
    <div className="container">
      <h1>Challenges</h1>
      <ul className="list-group">
        {challenges.map((challenge) => (
          <li
            key={challenge.id}
            className="list-group-item list-group-item-action"
            onClick={() => handleRowClick(challenge.endpoint.substring(1))} // Remove leading slash
          >
            <div>{challenge.name}</div>
            <div>{challenge.description}</div>
            {challenge.endpoint && <div>URL: {challenge.endpoint}</div>}
          </li>
        ))}
      </ul>
      <div className="d-flex justify-content-between mt-3">
        <button
          className="btn btn-primary"
          onClick={handlePreviousPage}
          disabled={currentPage === 0}
        >
          Previous
        </button>
        <button
          className="btn btn-primary"
          onClick={handleNextPage}
          disabled={currentPage === totalPages - 1}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default Challenges;
