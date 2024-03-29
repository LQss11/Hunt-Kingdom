<?php

namespace ForumBundle\Repository;

/**
 * publicationRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class publicationRepository extends \Doctrine\ORM\EntityRepository
{
    public function findEntitiesByString($str){
        return $this->getEntityManager()
            ->createQuery(
                'SELECT p
                FROM ForumBundle:Publication p
                WHERE p.contenu LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }

    public function mefind($name){
        $query=$this->getEntityManager()
            ->createQuery("select m from ForumBundle:publication m where m.contenu like :name ");
        $query->setParameter(":name",'%'.$name.'%');
        return $query->getResult();
    }
}
