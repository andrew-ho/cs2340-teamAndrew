using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {

    CharacterController characterController;
    float movementSpeed = 5.0f;
    float verticalVelocity = 0f;
    float jumpSpeed = 5.0f;
	// Use this for initialization
	void Start () {
        characterController = GetComponent<CharacterController>();

	}
	
	// Update is called once per frame
	void Update () {
        float forwardSpeed = Input.GetAxis("Vertical") * movementSpeed;
        float sideSpeed = Input.GetAxis("Horizontal") * movementSpeed;
        verticalVelocity += Physics.gravity.y * Time.deltaTime;
        if (characterController.isGrounded && Input.GetButton("Jump"))
        {
            verticalVelocity = jumpSpeed;
        }
        
        Vector3 speed = new Vector3(sideSpeed, verticalVelocity, 0);
        characterController.Move(speed * Time.deltaTime);
    }
}
