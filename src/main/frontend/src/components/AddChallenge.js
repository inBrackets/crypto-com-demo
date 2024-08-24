import axios from "axios";
import { useState } from "react";

function AddChallenge() {
  const [month, setMonth] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/challenges", {
        month,
        description,
      });
      setMonth("");
      setDescription("");
    } catch (error) {
      console.error("Error adding challenge: ", error);
    }
  };

  return (
    <div className="card my-5">
      <div className="card-header">Add new Challenge</div>
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="month" className="form-label">
              Month
            </label>
            <input
              type="text"
              className="form-control"
              placeholder="e.g., January"
              id="month"
              value={month}
              onChange={(e) => setMonth(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Description
            </label>
            <textarea
              type="text"
              className="form-control"
              placeholder="Describe the challenge"
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddChallenge;
