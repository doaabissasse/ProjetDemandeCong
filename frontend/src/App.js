import React, { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    // Fetch data from the API endpoint
    fetch('/api/message')
      .then(response => response.json())
      .then(data => setMessage(data.message))
      .catch(error => console.error('Error fetching the API:', error));
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>{message || 'Loading...'}</h1>
      </header>
    </div>
  );
}

export default App;
