import './App.css'
import {useState} from "react";
import { Streamdown } from 'streamdown';

interface Message{
    type:"user"|"assistant",
    content:string
}

function AppSSE() {

    const [question, setQuestion] = useState('')
    //const [response,SetResponse] = useState('')
    const [think,setThink] = useState(false)
    const [messages,SetMessages] = useState<Message[]>([])

    function send() {
        const lastQuestion = question
        if(lastQuestion.trim()=='')
            return
        SetMessages( pre=>{
            return [...pre,{
                type:"user",
                content:lastQuestion
            }]
        })
        setQuestion('')
        setThink(true)

        const es =new EventSource(`${import.meta.env.VITE_AGENT_URL}/stream?msg=${encodeURIComponent(question)}`)
        let assistantResp='';
        es.onmessage=(event)=>{
            //console.log(event.data);

            if(event.data==='[complete]'){
                setThink(false)
                es.close()
                return
            }

            assistantResp += event.data
            SetMessages( pre=>{
                return [...pre.slice(0,-1),{
                    type:"assistant",
                    content:assistantResp
                }]
            })
        }

        es.onopen=()=>{
            setThink(true)
            SetMessages( pre=>{
                return [...pre,{
                    type:"assistant",
                    content:''
                }]
            })
        }

        es.onerror=(event)=>{
            console.error(event)
            setThink(false)
            es.close()
        }

        /*fetch(`${import.meta.env.VITE_AGENT_URL}/stream?msg=${encodeURIComponent(question)}`).then(resp=>{
            let reader = resp.body?.getReader();
            let textDecoder = new TextDecoder();
            let assistantResp='';
            SetMessages( pre=>{
                return [...pre,{
                    type:"assistant",
                    content:''
                }]
            })
            reader?.read().then(function processText({ done, value }):void {
                if (done) {
                    setThink(false)
                    return;
                }
                //SetResponse(pre=>{return pre+ textDecoder.decode(value)})
                assistantResp += textDecoder.decode(value)

                SetMessages( pre=>{
                    return [...pre.slice(0,-1),{
                        type:"assistant",
                        content:assistantResp
                    }]
                })
                reader.read().then(processText);
            }).catch(e=>{
                setThink(false)
                console.error(e)
            });

        })*/
    }

  return (
      <>
          <div className="flex h-screen flex-col items-center justify-center">
              <div className="w-md">
                  <div className="flex flex-col gap-2 w-full h-128 overflow-auto border p-2">
                      {
                          messages.map(msg=>{
                              return (msg.type=="user"?
                                      <div className="flex"><div className="text-left bg-red-200 p-2 shadow rounded">{msg.content}</div></div>:
                                  <div className="flex justify-end">
                                      <Streamdown className="bg-blue-300 p-2 shadow rounded">{msg.content}</Streamdown>
                                  </div>
                              )
                          })
                      }

                  </div>

                  <div className="flex gap-2 mt-2">
                      <input className="border-1 p-2 w-full"
                             value={question}
                             onChange={e => setQuestion(e.target.value)}
                             placeholder="question..."/>
                      <button type="button" onClick={send} disabled={question.trim()=='' || think}
                              className={`border-1 p-2 ${(question.trim()!='' && !think)?'bg-amber-300 cursor-pointer rounded active:bg-amber-600':''}`}>send
                      </button>
                  </div>
              </div>
          </div>

      </>
  )
}

export default AppSSE
