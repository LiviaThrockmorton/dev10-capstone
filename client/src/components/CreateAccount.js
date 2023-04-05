import background from "./images/field_ducklings.jpg";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useState } from "react";

const baseUser = { appUserId: "", username: "", passwordHash: "", email: "", hidden: 0, enabled: 1 }

function CreateAccount() {

    // const [username, setUsername] = useState("");
    // const [password, setPassword] = useState("");
    // const [email, setEmail] = useState("");
    // const { appUserId } = useParams();
    const [confirm, setConfirm] = useState("");
    const [error, setError] = useState(false);
    const [newUser, setNewUser] = useState(baseUser);
    const navigate = useNavigate();


    function handleChange(evt) {
        const nextNewUser = { ...newUser };
        nextNewUser[evt.target.name] = evt.target.value;
        setNewUser(nextNewUser);
        console.log(nextNewUser);
    }

    function validateInput() {
        console.log(newUser.passwordHash, confirm);
        if (newUser.passwordHash !== confirm) {
            setError(true);
        } else {
            setError(false);
        }
    }

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        validateInput();
        const nextNewUser = { ...newUser };

        if (!error) {
            fetch('http://localhost:8080/create_account', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(nextNewUser)
            })
                .then(response => response.json())
                .then(data => { setNewUser({ ...nextNewUser, appUserId: data.appUserId }); })
                .then(navigate("/profile"))
                .catch(setError(true))
        }
    }



    return (
        <div className="container">
            <div className="row">
                <div className="col-6">
                    <h1 className="text-center">Create Account</h1>
                    <h5 className="text-center">Already have an account? Log in {' '}
                        <Link to="/login" className="btn btn-outline-secondary">here</Link>
                    </h5>

                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="username">Username:</label>
                            <input type="text" onChange={handleChange} className="form-control mb-4" id="username" name="username" placeholder="Enter a unique username" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="passwordHash">Password:</label>
                            <input type="password" onChange={handleChange} className="form-control mb-4" id="passwordHash" name="passwordHash" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="confirm">Confirm Password:</label>
                            <input type="password" onChange={(event) => setConfirm(event.target.value)} className="form-control mb-4" id="confirm" name="confirm" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="email">Email:</label>
                            <input type="enail" onChange={handleChange} className="form-control mb-4" id="email" name="email" />
                        </div>

                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>

                    {error && <p className="text-danger mt-3">Passwords don't match, or couldn't create an account</p>}
                </div>


                <div className="h-100 col-6 bg-image" style={{ backgroundImage: `url(${background})`, backgroundPosition: "center", backgroundSize: "cover" }}>
                    <div style={{ height: "800px" }}></div>
                </div>
            </div>
        </div>
    )
}

export default CreateAccount;