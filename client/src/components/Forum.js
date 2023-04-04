import React, { useState, useContext, useEffect } from "react";
import Outfit from "./Outfit";
import AuthContext from "../contexts/AuthContext";
import { findAll } from "../services/OutfitService";
import { useNavigate } from "react-router-dom";

function Forum() {

  const [outfits, setOutfits] = useState([]);
  const auth = useContext(AuthContext);
  const canDelete = auth.user && auth.user.hasRole("ADMIN");
  const navigate = useNavigate();
  const [error, setError] = useState(false);

  

  useEffect(() => {
    findAll()
      .then(setOutfits)
      .catch(() => setError(true));
  }, [navigate]);

  return (
    <div className="container">
      <div className="text-center">
        <h1>Forum</h1>
        <h4>Get inspiration and comment on posts</h4>
      </div>

      <div className="row">
        <div className="col d-flex flex-row">
          {outfits.map(o => <Outfit key={o.outfitId} outfit={o} canDelete={canDelete} viewOutfit={true} />)}
        </div>
        {error && <p className="text-danger">The ducks are tired of modeling, so they're napping right now</p>}
      </div>
    </div>
  );
}

export default Forum;
