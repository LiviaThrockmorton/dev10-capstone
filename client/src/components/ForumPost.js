import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState, useContext } from "react";
import Outfit from "./Outfit";
import { findByOutfit, add } from "../services/commentService";
import { findById } from "../services/OutfitService";
import AuthContext from "../contexts/AuthContext";
import Comment from "./Comment";

const baseComment = { commentId: "", userId: "", content: "", outfitId: "", dateTime: "", hidden: "false" }


function ForumPost() {

  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState(baseComment);
  const navigate = useNavigate();
  const [outfit, setOutfit] = useState(baseComment);
  const { outfitId } = useParams();
  const auth = useContext(AuthContext);
  const [displayResult, setDisplayResult] = useState();
  const [commentResult, setCommentResult] = useState();
  const canAdd = auth.user !== null;


  useEffect(() => {
    findById(outfitId)
      .then(setOutfit)
      .catch(() => setDisplayResult("We can't find that outfit right now"));

  }, [outfitId, navigate]);

  useEffect(() => {
    loadComments();
  }, [outfitId, navigate]);

  function loadComments() {
    findByOutfit(outfitId)
      .then(setComments)
      .catch(() => setDisplayResult("Failed to load comments"));

  }



  function handleChange(evt) {
    const nextComment = { ...comment };
    nextComment[evt.target.name] = evt.target.value;
    setComment(nextComment);
  }

  function handleSubmit(evt) {
    evt.preventDefault();
    const nextComment = { ...comment };
    nextComment.userId = auth.user.app_user_id;
    nextComment.outfitId = outfitId;

    add(nextComment)
      .then(() => {
        setCommentResult("Success! Comment saved.")
        loadComments();

        setComment(baseComment)

      })
  
      .catch(() => setCommentResult("Failure to save comment."))
  }

  return (
    <div className="row">
      <div className="col-6">
        <div className="comment-container">

          <div>
            {comments.map((c) => (<Comment key={c.commentId} comment={c} canAdd={canAdd} />))}
            {displayResult && <p className="col mt-4">{displayResult}</p>}
          </div>

          <div className="add-comment">
            {auth.user &&
              <div>
                <form>
                  <input type="text" onChange={handleChange}
                    className="form-control form-outline-light form" value={comment.content}
                    id="content" name="content" placeholder="Add a comment..."></input>

                  <button className="btn btn-success" onClick={handleSubmit}>Add</button>
                </form>
                {commentResult && <p className="col mt-4">{commentResult}</p>}
              </div>
            }

            <div>
              <center>
                <Link to="/forum">
                  <button type="button" className="btn btn-secondary">Back to Forum</button>
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

        {displayResult && <p className="col mt-4">{displayResult}</p>}

      </div>
    </div >
  );
}

export default ForumPost;

