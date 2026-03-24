import './App.css'
import {useState} from "react";
import { Streamdown } from 'streamdown';

function App() {

    const [question, setQuestion] = useState('')
    const [response,SetResponse] = useState('')

    function send() {
        fetch(`http://localhost:8080/stream?msg=${encodeURIComponent(question)}`).then(resp=>{
          let reader = resp.body?.getReader();
          let textDecoder = new TextDecoder();
            reader?.read().then(function processText({ done, value }):void {
                if (done) {
                    return;
                }
                SetResponse(pre=>{return pre+ textDecoder.decode(value)})
                reader.read().then(processText);
            });

        })
    }

  return (
    <>
      <Streamdown>{response}</Streamdown>
      <input value={question} onChange={e => setQuestion(e.target.value)} type="text"/>
      <button onClick={send}>send</button>
    </>
  )
}

export default App
