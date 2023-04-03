import { Link } from "react-router-dom";
import background from "./images/sink_ducklings.jpg";

function ForumPost() {
  return (
    <>
    <div class="row">
        <div class="col-md-4 offset-md-8">
          <h1>username here</h1>
        </div>
        {" "}
      </div>

      <div className="row">
        <div className="col-6">
          <div class="comment-container">
            <div class="comment">
              <div class="username-date">
                <span class="username">John Doe</span>
                <span class="date">April 2, 2023</span>
              </div>
              <div class="comment-text">
                <p>This is the first comment.</p>
              </div>
            </div>
            <div class="comment">
              <div class="username-date">
                <span class="username">Jane Smith</span>
                <span class="date">April 1, 2023</span>
              </div>
              <div class="comment-text">
                <p>This is the second comment.</p>
              </div>
            </div>
            <div class="comment">
              <div class="username-date">
                <span class="username">Bob Johnson</span>
                <span class="date">March 31, 2023</span>
              </div>
              <div class="comment-text">
                <p>This is the third comment.</p>
              </div>
            </div>
            <div class="see-more">
              <a href="#">See more</a>
            </div>
            <div class="add-comment">
              <div>
                <form>
                  <input type="text" placeholder="Add a comment..."></input>
                  <button type="submit">Add</button>
                </form>
              </div>
              <div>
                <center>
                  <Link to="/forum">
                <button type="button" class="back-button">
                  Back to Forum
                </button>
                </Link>
                </center>
              </div>
            </div>
          </div>
        </div>

        <div className="col-6">
          <div
            className="h-100 bg-image"
            style={{
              backgroundImage: `url(${background})`,
              backgroundPosition: "center",
              backgroundSize: "cover",
            }}
          >
          </div>
        </div>
      </div>
    </>
  );
}

export default ForumPost;
