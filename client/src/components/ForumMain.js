import { Link } from "react-router-dom";
import brownbaby from "./images/brown_duckling.jpg";
import ForumPost from "./ForumPost";


const imageLinks = document.querySelectorAll('.image-link');

imageLinks.forEach((link) => {
  const commentBox = link.parentNode.querySelector('.comment-box');

  link.addEventListener('click', (event) => {
    event.preventDefault();
    commentBox.style.display = 'block';
  });
});

function ForumMain() {
  return (
    <>
      <div class="row justify-content-center">
        <div class="col-md-4">
          <center><h1>Forum</h1></center>
        </div>
        <div class="row justify-content-center">
          <div class="col-md-4">
            <center><h4>Get inspiration and comment on posts</h4></center>
            <center><h5>Click each post to comment!</h5></center>
          </div>
        </div>{" "}
      </div>

      <div className="row justify-content-center">
        <div className="col-6">
          <div class="container-main">
            <div>
                <Link to ="/Forum-Post">
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
              </Link>
            </div>

            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>
            
            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

          </div>
          <div class="container-main">
            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

            <div>
              <img src= {brownbaby} />
              <div class="image-text">
                <center>brown baby</center>
              </div>
            </div>

          </div>
        </div>
      </div>
    </>
  );
}

export default ForumMain;

