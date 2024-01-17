package org.example.collections.implementation;

import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.interfaces.QueueADT;
import org.example.collections.interfaces.StackADT;

public class QueueWithTwoStacks<T> implements QueueADT<T>
{
    /**
     * enqueue da stack
     */
    private StackADT<T> stack1;

    /**
     * dequeue da stack
     */
    private StackADT<T> stack2;


    public QueueWithTwoStacks()
    {
        stack1 = (StackADT<T>) new Stack<T>();
        stack2 = (StackADT<T>) new Stack<T>();
    }


    @Override
    public void enqueue(T element)
    {
        stack1.push(element);
    }

    @Override
    public T dequeue()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Stack");
        }

        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    @Override
    public T first()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Stack");
        }

        if(stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }

        return stack2.peek();
    }

    @Override
    public boolean isEmpty()
    {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    @Override
    public int size()
    {
        return stack1.size() + stack2.size();
    }
}

