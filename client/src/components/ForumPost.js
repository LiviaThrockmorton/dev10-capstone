import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState, useContext } from "react";
import Outfit from "./Outfit";
import { findByOutfit, save } from "../services/commentService";
import { findById } from "../services/OutfitService";
import AuthContext from "../contexts/AuthContext";
import Comment from "./Comment";

const baseComment = { commentId: "", userId: "", content: "", dateTime: null, hidden: "false" }


function ForumPost() {

  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState(baseComment);
  const navigate = useNavigate();
  const [error, setError] = useState(false);
  const [outfit, setOutfit] = useState([]);
  const { outfitId } = useParams();
  const [content, setContent] = useState(baseComment.content)
  const auth = useContext(AuthContext);
  const canDelete = auth.user && auth.user.hasAnyAuthority("ADMIN");
  const [saveResult, setSaveResult] = useState();

  function handleSubmit(evt) {
    evt.preventDefault();


    if (auth.user) {
      const nextComment = { ...comments };
      nextComment.userId = auth.user.app_user_id;
      nextComment.content = comments.content;



      console.log(auth.user)

      console.log('next comment', nextComment);
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

            
            {auth.user &&

              <div>

                <form >

                  <input type="text" value={comments.content} class="form-control" id="content" name="content" placeholder="Add a comment..."></input>

                  <button className="btn btn-success " onClick={handleSubmit}>Add</button>

                </form>

                {saveResult && <p className="col mt-4">{saveResult}</p>}

              </div>

            }


            <div className="add-comment">

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

