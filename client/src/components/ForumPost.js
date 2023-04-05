import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Outfit from "./Outfit";
import { findByOutfit } from "../services/commentService";
import { findById } from "../services/OutfitService";


function ForumPost({ outfitId }) {

  const [comments, setComments] = useState([]);
  const navigate = useNavigate();
  const [error, setError] = useState(false);
  const [outfit, setOutfit] = useState([]);

  function handleSubmit(evt) {
    evt.preventDefault();
    console.log("Add comment placeholder")
}

  useEffect(() => {

    findById(outfitId)
      .then(setOutfit)
      .catch(() => setError(true));

  }, [outfitId, navigate]);

  // useEffect(() => {
  //   findByOutfit(outfitId)
  //     .then(setComments)
  //     .catch(() => setError(true));
  // }, [navigate]); // this will happen only once when the component is loaded









  return (
    <>
      <div className="row">
        <div className="col-md-4 offset-md-8">
          <h1> </h1>
        </div>
        {" "}
      </div>

      <div className="row">
        <div className="col-6">
          <div className="comment-container">


            {/* < div >


              {comments.map((comment) => (
                <Comment key={comment.commentId} comment={comment} />
              ))}

            </div> */}






            <div className="add-comment">
              <div>
                <form onSubmit={handleSubmit}>
                  <input type="text" placeholder="Add a comment..."></input>
                  <button type="submit">Add</button>
                </form>
              </div>
              <div>
                <center>
                  <Link to="/forum">
                    <button type="button" className="back-button">
                      Back to Forum
                    </button>
                  </Link>
                </center>
              </div>
            </div>
          </div>
        </div>

        <div className="col-6">
          <div style={{ width: "800px", height: "1000px" }}>
            {<Outfit key={outfit.outfitId} outfit={outfit} viewOutfit={false} />}
          </div>
        </div>
      </div >
    </>
  );
}

export default ForumPost;


//TODO
///Showing the outfit-- id is undefined but is part of the url

