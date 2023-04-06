import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState, useContext } from "react";
import Outfit from "./Outfit";
import { findByOutfit, save } from "../services/commentService";
import { findById} from "../services/OutfitService";
import AuthContext from "../contexts/AuthContext";
import Comment from "./Comment";

const baseComment = { commentId: "", userId: "", content: "", dateTime: null, hidden: "false" }


function ForumPost() {

  const [comments, setComments] = useState([]);
  const navigate = useNavigate();
  const [error, setError] = useState(false);
  const [outfit, setOutfit] = useState(baseComment);
  const { outfitId } = useParams();
  const auth = useContext(AuthContext);
  const canDelete = auth.user && auth.user.hasAnyAuthority("ADMIN");
  const [saveResult, setSaveResult] = useState();

  function handleSubmit(evt) {
    evt.preventDefault();
















    if (auth.user) {
      const nextComment = { ...comments };
      nextComment.userId = auth.user.app_user_id;

      console.log(auth.user)

      console.log(nextComment);
      console.log(...comments);

      save(nextComment)
        .then(() => setSaveResult("Success! Comment saved."))
        .catch(() => setSaveResult("Failure to save comment."))
    } else {
      navigate("/login")
    }

  }
  




//sets the outfit
  useEffect(() => {

    findById(outfitId)
      .then(setOutfit)
      .catch(() => setError(true));

  }, [outfitId, navigate]);


//should only be finding the comments
  useEffect(() => {
    findByOutfit(outfitId)
      .then(setComments)
      .catch(() => setError(true));
  }, [comments.outfitId, navigate]); // this will happen only once when the component is loaded



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


            < div >

              {comments.map((comment) => (
                <Comment key={comment.commentId} comment={comment} commentUserId={comment.userId} />
              ))}

            </div>


            <div className="add-comment">
              <div>
                <form >
                  <input class="form-control form-outline-light form"  placeholder="Add a comment..."></input>
                  <button className="btn btn-success" onClick={handleSubmit}>Add</button>
                </form>
                {saveResult && <p className="col mt-4">{saveResult}</p>}
              </div>
              <div>
                <center>
                  <Link to="/forum">
                    <button type="button" className="btn btn-secondary">
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

