import { useState, useEffect } from "react";
import axios from "axios";

const Challenges = () => {
  interface Challenge {
    id: number;
    title: string;
    description: string;
    url: string;
  }

  interface PaginatedResponse {
    content: Challenge[];
    pageable: {
      pageNumber: number;
      pageSize: number;
    };
    totalPages: number;
    totalElements: number;
    numberOfElements: number;
  }

  const [challenges, setChallenges] = useState<Challenge[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

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

  return (
    <div>
      <h1>Challenges</h1>
      <ul>
        {challenges.map((challenge, index) => (
          <li key={index}>
            <div>Input: {challenge.title}</div>
            <div>Output: {challenge.description}</div>
            {challenge.url && <div>URL: {challenge.url}</div>}
          </li>
        ))}
      </ul>
      <div>
        <button onClick={handlePreviousPage} disabled={currentPage === 0}>
          Previous
        </button>
        <button
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
