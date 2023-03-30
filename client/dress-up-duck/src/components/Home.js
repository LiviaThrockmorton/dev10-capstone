import { Link } from "react-router-dom";
import background from "./images/sink_ducklings.jpg";

function Home() {
    return (
        <div className="container w-100 h-100">
            <div className="row">
                <div className="col-6">
                    <h1 className="text-center">Dress-Up Duck</h1>
                    <h5 className="text-center">Create new outfits, get inspiration from others, and more</h5>
                </div>

                <div className="col-6 bg-image" style={{ backgroundImage: `url(${background})` }}>
                    <Link to="/create-account" className="btn btn-primary">Sign Up</Link>
                    <Link to="/login" className="btn btn-primary">Log In</Link>
                    <Link to="/forum" className="btn btn-primary">Forum</Link>
                    <Link to="/dress-up-duck" className="btn btn-primary">Dress Up!</Link>
                </div>
            </div>
        </div>
    )
}

export default Home;