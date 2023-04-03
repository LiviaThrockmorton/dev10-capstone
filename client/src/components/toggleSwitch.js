import React, { useState } from "react";

const ToggleSwitch = () => {
  const [isChecked, setIsChecked] = useState(false);

  const handleChange = () => {
    setIsChecked(prevState => !prevState);
  };

  return (
    <label className="switch">
      <input type="checkbox" checked={isChecked} onChange={handleChange} />
      <span className="slider round">
        <span className="switch-label">Posted</span>
        {isChecked && (
          <span className="switch-label-right">Private</span>
        )}
      </span>
    </label>
  );
};

export default ToggleSwitch;
