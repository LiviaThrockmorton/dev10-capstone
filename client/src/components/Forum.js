import React, { useState, useContext, useEffect } from "react";
import Outfit from "./Outfit";
import AuthContext from "../contexts/AuthContext";
import { findAll } from "../services/OutfitService";
import { useNavigate } from "react-router-dom";



function Forum() {
  const [outfits, setOutfits] = useState([]);
  const auth = useContext(AuthContext);
  const canDelete = auth.user && auth.user.hasAnyAuthority("ADMIN");
  const navigate = useNavigate();
  const [error, setError] = useState(false);

  useEffect(() => {
    findAll()
      .then(setOutfits)
      .catch(() => setError(true));
  }, [navigate]);

  return (
    <div className="flex-container">
      <div>
        <h1 className="text-center">Forum</h1>
        <h4 className="text-center">Get inspiration and comment on posts!</h4>
      </div>

      <div className="d-flex flex-wrap">
        {outfits.map((o) => (
          <Outfit
            key={o.outfitId}
            outfit={o}
            canDelete={canDelete}
            viewOutfit={true}
            profileView={false}
          />
        ))}
      </div>
      {error && (
        <p className="text-danger">
          The ducks are tired of modeling, so they're napping right now
        </p>
      )}
    </div>
  );
}

export default Forum;
