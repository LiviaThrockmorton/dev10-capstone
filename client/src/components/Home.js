import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import background from "./images/sink_ducklings.jpg";

import Outfit from "./Outfit";
import { findAll } from "../services/OutfitService";
import { useNavigate } from "react-router-dom";

function Home() {

const [outfits, setOutfits] = useState([]);
const navigate = useNavigate();
const [error, setError] = useState(false);


useEffect(() => {
    findAll()
      .then(setOutfits)
      .catch(() => setError(true));
  }, [navigate]);

  return (
    <div className="container">
      <div className="row h-100">
        <div className="col-6">
          <h1 className="text-center">Dress-Up Duck</h1>
          <h5 className="text-center">
            Create new outfits, get inspiration from others, and more
          </h5>
          <div className="d-flex flex-wrap">
            <div className="d-flex flex-wrap">
              {outfits.map((o) => (
                <Outfit
                  key={o.outfitId}
                  outfit={o}
                  viewOutfit={false}
                  viewHome={true}
                />
              ))}
            </div>
          </div>
        </div>
  
        <div
          className="h-100 col-6 bg-image"
          style={{
            backgroundImage: `url(${background})`,
            backgroundPosition: "center",
            backgroundSize: "cover",
          }}
        >
          <div
            className="d-flex align-items-center flex-column mt-5"
            style={{ backgroundColor: "rgba(255,255,255, .75)" }}
          >
            <Link to="/create-account" className="btn btn-primary mb-4 mt-5">
              Sign Up
            </Link>
            <Link to="/login" className="btn btn-primary mb-4">
              Log In
            </Link>
            <Link to="/forum" className="btn btn-primary mb-4">
              Forum
            </Link>
            <Link to="/dress-up-duck" className="btn btn-primary mb-4">
              Dress Up!
            </Link>
            <div style={{ height: "800px" }}></div>
          </div>
        </div>
      </div>
    </div>
  );
  
}

export default Home;
