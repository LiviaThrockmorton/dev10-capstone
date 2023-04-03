import { useLocation } from "react-router-dom";

function Success() {

  const location = useLocation();

  return <p className="text-success">Success! {location.state ? location.state.msg : "✅"}</p>;
}

export default Success;